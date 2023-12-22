package id.lutfuel.app.ui.tripDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.TripDetailResponse
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AsyncState<TripDetailResponse>>(AsyncState.Loading())
    val state: StateFlow<AsyncState<TripDetailResponse>> = _state

    fun getTripDetail(id: Int) = viewModelScope.launch {
        _state.value = AsyncState.Loading()
        try {
            _state.value = AsyncState.Success(repository.getHistoryDetail(id))
        } catch (e: Exception) {
            _state.value = AsyncState.Error(e)
        }
    }
}