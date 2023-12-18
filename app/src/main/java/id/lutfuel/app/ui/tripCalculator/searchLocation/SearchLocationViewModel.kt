package id.lutfuel.app.ui.tripCalculator.searchLocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.SearchLocationResponseItem
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(
    private val repository: LutFuelRepository,
) : ViewModel() {
    private val _result = MutableStateFlow<AsyncState<List<SearchLocationResponseItem>>?>(null)
    val result: StateFlow<AsyncState<List<SearchLocationResponseItem>>?> = _result

    fun searchLocation(query: String) =
        viewModelScope.launch {
            _result.value = AsyncState.Loading()
            try {
                val result = repository.searchLocation(query)
                _result.value = AsyncState.Success(result)
            } catch (e: Exception) {
                _result.value = AsyncState.Error(e)
            }
        }
}