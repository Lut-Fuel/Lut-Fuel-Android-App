package id.lutfuel.app.ui.bottomNav

import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavBarViewModel @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    val firebaseAuth: FirebaseAuth
) : ViewModel() {

    fun signOut() {
        googleSignInClient.signOut()
        firebaseAuth.signOut()
    }
}