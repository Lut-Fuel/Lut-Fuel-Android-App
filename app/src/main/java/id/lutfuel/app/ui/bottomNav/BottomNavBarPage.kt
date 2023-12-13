package id.lutfuel.app.ui.bottomNav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.destinations.OnboardingPageDestination

@Destination
@Composable
fun BottomNavBarPage(
    navigator: DestinationsNavigator,
    viewModel: BottomNavBarViewModel = hiltViewModel()
) {
    val auth = viewModel.firebaseAuth
    DisposableEffect(key1 = auth) {
        val authStateListener = FirebaseAuth.AuthStateListener { authState ->
            if (authState.currentUser == null) {
                navigator.popBackStack()
                navigator.navigate(OnboardingPageDestination())
            }
        }
        auth.addAuthStateListener(authStateListener)

        onDispose {
            auth.removeAuthStateListener(authStateListener)
        }
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = LutFuelColor.primaryDefault,
                contentColor = LutFuelColor.primaryWhite,
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "Start Trip",
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        },
        bottomBar = {
            val navigationBarItemColors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.White,
                selectedTextColor = LutFuelColor.primaryDefault,
                selectedIconColor = LutFuelColor.primaryDefault,
            )
            NavigationBar(
                containerColor = LutFuelColor.primaryWhite,
                contentColor = LutFuelColor.primaryDefault,
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Home") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Home, "Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text(text = "History") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Refresh, "History") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Notification") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Notifications, "Notification") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Profile") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Person, "Profile") }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column {
                    Text(text = auth.currentUser?.email ?: "No Email")
                    Text(text = "BottomNavBarPage")
                    Button(onClick = { viewModel.signOut() }) {
                        Text(text = "Sign Out")
                    }
                }
            }
        }
    }
}