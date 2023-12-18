package id.lutfuel.app.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import id.lutfuel.app.core.theme.LutFuelTheme

@Composable
@Destination
fun ProfilePage(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
//    TODO: Add profile page
    Column {
        Text(text = Firebase.auth.currentUser?.email ?: "No Email")
        Button(onClick = {
            viewModel.signOut()
        }) {
            Text(text = "Sign Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    LutFuelTheme {
        ProfilePage(navigator = EmptyDestinationsNavigator)
    }
}