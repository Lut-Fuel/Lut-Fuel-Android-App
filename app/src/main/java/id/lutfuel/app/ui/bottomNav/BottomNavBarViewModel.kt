package id.lutfuel.app.ui.bottomNav

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.repository.LutFuelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BottomNavBarViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _isSignedOut = MutableStateFlow(false)
    val isSignedOut: StateFlow<Boolean> = _isSignedOut

    private val authListener = FirebaseAuth.AuthStateListener { authState ->
        if (authState.currentUser == null) {
            _isSignedOut.value = true
        }
    }

    init {
        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun onCleared() {
        super.onCleared()
        firebaseAuth.removeAuthStateListener(authListener)
    }


    private val _navbarIndex = MutableStateFlow(0)
    val navbarIndex: StateFlow<Int> = _navbarIndex

    fun setNavbarIndex(index: Int) {
        _navbarIndex.value = index
    }
}