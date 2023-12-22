package id.lutfuel.app.ui.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.repository.LutFuelRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: LutFuelRepository,
    val firebaseAuth: FirebaseAuth
) : ViewModel() {
    val user = firebaseAuth.currentUser!!
    fun signOut() {
        repository.signOut()
    }
}