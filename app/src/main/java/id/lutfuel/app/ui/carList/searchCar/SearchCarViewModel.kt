package id.lutfuel.app.ui.carList.searchCar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.CarListResponseItem
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCarViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _result = MutableStateFlow<AsyncState<List<CarListResponseItem>>?>(null)
    val result: StateFlow<AsyncState<List<CarListResponseItem>>?> = _result

    fun searchCar(query: String) = viewModelScope.launch {
        _result.value = AsyncState.Loading()
        try {
            val result = repository.searchCar(query)
            _result.value = AsyncState.Success(result)
        } catch (e: Exception) {
            _result.value = AsyncState.Error(e)
        }
    }
}