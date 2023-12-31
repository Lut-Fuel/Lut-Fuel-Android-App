package id.lutfuel.app.di

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.location.LocationServices
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.lutfuel.app.BuildConfig
import id.lutfuel.app.LutFuelApp
import id.lutfuel.app.data.api.ApiService
import id.lutfuel.app.data.repository.LutFuelRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApplication() = LutFuelApp()

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://lutfuel-backend-submit-tcpz3v6bgq-et.a.run.app")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(app: Application): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(BuildConfig.oauthClientId)
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(app, gso)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFuseLocationProviderClient(app: Application) =
        LocationServices.getFusedLocationProviderClient(app)

    @Provides
    @Singleton
    fun provideLutFuelRepository(
        apiService: ApiService,
        firebaseAuth: FirebaseAuth,
        googleSignInClient: GoogleSignInClient
    ) =
        LutFuelRepository(apiService, firebaseAuth, googleSignInClient)
}