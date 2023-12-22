package id.lutfuel.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.model.response.HomeResponse
import id.lutfuel.app.data.repository.LutFuelRepository
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LutFuelRepository,
    val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private val _state = MutableStateFlow<AsyncState<HomeResponse>>(AsyncState.Loading())
    val state: StateFlow<AsyncState<HomeResponse>> = _state

    init {
        getHome()
    }

    fun getHome() = viewModelScope.launch {
        try {
            _state.value = AsyncState.Loading()
            val response = repository.getHome()
            _state.value = AsyncState.Success(response)
        } catch (e: Exception) {

            _state.value = AsyncState.Error(e)
        }
    }
}