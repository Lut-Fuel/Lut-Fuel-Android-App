package id.lutfuel.app.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.lutfuel.app.data.repository.LutFuelRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: LutFuelRepository
) : ViewModel() {
    fun signOut() {
        repository.signOut()
    }
}