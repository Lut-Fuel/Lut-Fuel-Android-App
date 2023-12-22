package id.lutfuel.app.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.HistoryListResponseItem
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _state =
        MutableStateFlow<AsyncState<List<HistoryListResponseItem>>>(AsyncState.Loading())
    val state: StateFlow<AsyncState<List<HistoryListResponseItem>>> = _state

    init {
        getHistory()
    }

    fun getHistory() = viewModelScope.launch {
        _state.value = AsyncState.Loading()
        try {
            _state.value = AsyncState.Success(repository.getHistories())
        } catch (e: Exception) {
            _state.value = AsyncState.Error(e)
        }
    }
}