package id.lutfuel.app.ui.carList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.CarListResponseItem
import id.lutfuel.app.data.model.response.UsersCarListResponseItem
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.carList.addCar.AddCarState
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCarListViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _state =
        MutableStateFlow<AsyncState<List<UsersCarListResponseItem>>>(AsyncState.Loading())
    val state: StateFlow<AsyncState<List<UsersCarListResponseItem>>> = _state

    private val _addCarState = MutableStateFlow(AddCarState())
    val addCarState: StateFlow<AddCarState> = _addCarState

    init {
        getUsersCars()
    }

    fun getUsersCars() = viewModelScope.launch {
        _state.value = AsyncState.Loading()
        try {
            _state.value = AsyncState.Success(repository.getUsersCars())
        } catch (e: Exception) {
            _state.value = AsyncState.Error(e)
        }
    }

    fun setSelectedCar(car: CarListResponseItem) {
        _addCarState.value = _addCarState.value.copy(
            selectedCarId = car.id,
            selectedCarName = car.carName,
        )
    }

}