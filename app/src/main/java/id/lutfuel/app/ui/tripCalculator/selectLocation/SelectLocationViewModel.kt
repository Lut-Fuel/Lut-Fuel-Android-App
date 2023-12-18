package id.lutfuel.app.ui.tripCalculator.selectLocation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.GetRoutesResponseItem
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import id.lutfuel.app.ui.tripCalculator.searchLocation.SearchLocationPageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SelectLocationViewModel @Inject constructor(
    private val repository: LutFuelRepository,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : ViewModel() {
    private val _state = MutableStateFlow(SelectLocationState())
    val state: StateFlow<SelectLocationState> = _state

    @SuppressLint("MissingPermission")
    fun getUsersLocation() = viewModelScope.launch {
        if (_state.value.from != null) return@launch
        val location = fusedLocationProviderClient.lastLocation.await()
        if (location != null) {
            _state.value = _state.value.copy(
                from = SelectedLocation(
                    name = "Your Location",
                    latitude = location.latitude,
                    longitude = location.longitude
                ),

                )
            animateCamera(
                latLng = LatLng(
                    location.latitude,
                    location.longitude
                )
            )
        }

    }

    fun onLocationSelected(
        location: SelectedLocation,
        type: SearchLocationPageType
    ) {
        when (type) {
            SearchLocationPageType.FROM -> {
                _state.value = _state.value.copy(from = location)
            }

            SearchLocationPageType.DESTINATION -> {
                _state.value = _state.value.copy(destination = location)
            }
        }
        if (_state.value.showFooter) {
            fetchRoutes()
            animateBetweenLocations(
                from = _state.value.from!!.latLng,
                destination = _state.value.destination!!.latLng
            )
        }
    }

    private fun fetchRoutes() = viewModelScope.launch {
        _state.value = _state.value.copy(routes = AsyncState.Loading())
        try {
            val response = repository.getRoutes(
                fromLatitude = _state.value.from!!.latitude,
                fromLongitude = _state.value.from!!.longitude,
                destinationLatitude = _state.value.destination!!.latitude,
                destinationLongitude = _state.value.destination!!.longitude
            )
            _state.value = _state.value.copy(
                routes = AsyncState.Success(response),
                shownPolyline = response.first().decodedPolyline
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(routes = AsyncState.Error(e))
        }
    }

    fun onRouteSelected(routeId: Int) {
        _state.value = _state.value.copy(
            selectedRouteId = routeId,
            shownPolyline = when (_state.value.routes!!) {
                is AsyncState.Success -> (_state.value.routes as AsyncState.Success<List<GetRoutesResponseItem>>).data.first { it.id == routeId }.decodedPolyline
                else -> _state.value.shownPolyline
            }
        )
        Log.d("SelectLocationViewModel", "onRouteSelected: ${_state.value.shownPolyline}")
    }

    private fun animateBetweenLocations(
        from: LatLng,
        destination: LatLng,
    ) = viewModelScope.launch {
        val bounds = LatLngBounds.builder()
            .include(from)
            .include(destination)
            .build()
        val padding = 200
        Log.d("SelectLocationViewModel", "animateBetweenLocations: $bounds")
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        _state.value.cameraPositionState.animate(
            update = cameraUpdate,
            durationMs = 2000
        )
        Log.d("SelectLocationViewModel", "animateBetweenLocations: $cameraUpdate")
    }

    private fun animateCamera(
        latLng: LatLng,
        zoom: Float = 15f,
        durationMs: Int = 2000
    ) = viewModelScope.launch {
        _state.value.cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                latLng,
                zoom
            ),
            durationMs = durationMs,
        )
    }


}