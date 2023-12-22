package id.lutfuel.app.ui.carList.addCar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.request.AddUserCarRequest
import id.lutfuel.app.data.model.response.CarListResponseItem
import id.lutfuel.app.data.repository.LutFuelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AddCarState())
    val state: StateFlow<AddCarState> = _state

    fun onSelectCar(car: CarListResponseItem) {
        _state.value = _state.value.copy(
            selectedCarId = car.id,
            selectedCarName = car.carName
        )
    }

    fun onSubmit(customName: String, fuelId: Int) = viewModelScope.launch {
        try {
            _state.value = _state.value.copy(
                success = false
            )
            val response = repository.addUsersCars(
                AddUserCarRequest(
                    customName = customName,
                    carId = _state.value.selectedCarId!!,
                    fuelId = fuelId
                )
            )
            _state.value = _state.value.copy(
                success = true
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                success = false
            )
        }
    }
}