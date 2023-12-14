package id.lutfuel.app.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import id.lutfuel.app.data.api.ApiService

class LutFuelRepository(
    private val apiService: ApiService,
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) {
    fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        val account = task.getResult(ApiException::class.java)!!
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
    }

    fun signOut() {
        googleSignInClient.signOut()
        firebaseAuth.signOut()
    }
}