package id.lutfuel.app.data.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import id.lutfuel.app.data.api.ApiService
import id.lutfuel.app.data.model.request.CalculateCostRequest
import id.lutfuel.app.data.model.response.GetRoutesResponseItem
import id.lutfuel.app.data.model.response.HomeResponse
import id.lutfuel.app.data.model.response.SearchLocationResponseItem
import id.lutfuel.app.data.model.response.TripDetailResponse
import id.lutfuel.app.data.model.response.UsersCarListResponseItem
import kotlinx.coroutines.tasks.await

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

    private suspend fun getTokenHeader(): String {
        val token = firebaseAuth.currentUser!!.getIdToken(true).await().token!!
        return "Bearer $token"
    }

    suspend fun getHome(): HomeResponse {
        return apiService.getHome(
            token = getTokenHeader()
        ).data
    }

    suspend fun searchLocation(query: String): List<SearchLocationResponseItem> {
        return apiService.searchLocation(
            token = getTokenHeader(),
            query = query
        ).data
    }

    suspend fun getRoutes(
        fromLatitude: Double,
        fromLongitude: Double,
        destinationLatitude: Double,
        destinationLongitude: Double,
    ): List<GetRoutesResponseItem> {
        return apiService.getRoutes(
            token = getTokenHeader(),
            fromLatitude = fromLatitude,
            fromLongitude = fromLongitude,
            destinationLatitude = destinationLatitude,
            destinationLongitude = destinationLongitude,
        ).data
    }

    suspend fun getUsersCars(): List<UsersCarListResponseItem> {
        return apiService.getUsersCars(
            token = getTokenHeader()
        ).data
    }

    suspend fun calculateCost(
        request: CalculateCostRequest
    ): TripDetailResponse {
        return apiService.calculateCost(
            token = getTokenHeader(),
            request = request
        ).data
    }

    suspend fun historyDetail(id: Int): TripDetailResponse {
        return apiService.historyDetail(
            token = getTokenHeader(),
            id = id
        ).data
    }
}