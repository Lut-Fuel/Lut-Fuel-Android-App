package id.lutfuel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import id.lutfuel.app.core.theme.LutFuelTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LutFuelTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

    private fun getGoogleLoginAuth(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.oauth_client_id))
            .requestId()
            .requestProfile()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }
}

//@RootNavGraph(start = true)
//@Destination
//@Composable
//fun Screen1(
//    navigator: DestinationsNavigator,
//) {
//    Column {
//        Text(text = "Screen 1")
//        Button(onClick = {
//            navigator.navigate(Screen2Destination())
//        }) {
//            Text(text = "Go to Screen 2")
//        }
//    }
//
//}
//
//@Destination
//@Composable
//fun Screen2(
//    navigator: DestinationsNavigator,
//) {
//    Column {
//        Text(text = "Screen 2")
//        Button(onClick = {
//            navigator.popBackStack()
//            navigator.navigate(Screen3Destination())
//        }) {
//            Text(text = "Go to Screen 3")
//
//        }
//    }
//
//}

@Destination
@Composable
fun Screen3(
    navigator: DestinationsNavigator,
) {
    Text(text = "Screen 3")
}