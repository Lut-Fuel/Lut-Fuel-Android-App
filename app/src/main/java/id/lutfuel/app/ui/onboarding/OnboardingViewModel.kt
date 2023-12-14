package id.lutfuel.app.ui.onboarding

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.repository.LutFuelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    internal val firebaseAuth: FirebaseAuth,
    val googleSignInClient: GoogleSignInClient,
    private val repository: LutFuelRepository
) : ViewModel() {
    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn

    private val authStateListener = FirebaseAuth.AuthStateListener {
        _isSignedIn.value = it.currentUser != null
    }

    init {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        repository.handleGoogleSignInResult(task)
    }

    override fun onCleared() {
        super.onCleared()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}