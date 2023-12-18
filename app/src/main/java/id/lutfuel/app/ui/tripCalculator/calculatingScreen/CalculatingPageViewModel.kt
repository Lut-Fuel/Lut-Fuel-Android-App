package id.lutfuel.app.ui.tripCalculator.calculatingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.request.CalculateCostRequest
import id.lutfuel.app.data.model.response.TripDetailResponse
import id.lutfuel.app.data.repository.LutFuelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatingPageViewModel @Inject constructor(
    private val repository: LutFuelRepository,
) : ViewModel() {
    private val _result = MutableStateFlow<TripDetailResponse?>(null)
    val result: StateFlow<TripDetailResponse?> = _result

    suspend fun calculateCost(
        request: CalculateCostRequest
    ) = viewModelScope.launch {
        val response = repository.calculateCost(request)
        _result.value = response
    }
}