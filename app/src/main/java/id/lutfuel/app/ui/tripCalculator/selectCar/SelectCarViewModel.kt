package id.lutfuel.app.ui.tripCalculator.selectCar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCarViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SelectCarState())
    val state: StateFlow<SelectCarState> = _state

    init {
        getUsersCars()
    }

    fun onSelectCar(usersCarId: Int) {
        _state.value = _state.value.copy(
            selectedCarId = usersCarId
        )
    }

    fun getUsersCars() = viewModelScope.launch {
        try {
            _state.value = _state.value.copy(
                usersCars = AsyncState.Loading()
            )
            val response = repository.getUsersCars()
            _state.value = _state.value.copy(
                usersCars = AsyncState.Success(response)
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                usersCars = AsyncState.Error(e)
            )
        }
    }
}