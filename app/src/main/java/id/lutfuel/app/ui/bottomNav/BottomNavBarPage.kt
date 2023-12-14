package id.lutfuel.app.ui.bottomNav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.ui.destinations.OnboardingPageDestination
import id.lutfuel.app.ui.maps.MapsPage
import id.lutfuel.app.ui.profile.ProfilePage

@Destination
@Composable
fun BottomNavBarPage(
    navigator: DestinationsNavigator,
    viewModel: BottomNavBarViewModel = hiltViewModel()
) {
    val isSignedOut by viewModel.isSignedOut.collectAsState()
    LaunchedEffect(isSignedOut) {
        if (isSignedOut) {
            navigator.popBackStack()
            navigator.navigate(OnboardingPageDestination)
        }
    }

    val navbarIndex by viewModel.navbarIndex.collectAsState()
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
                    selected = navbarIndex == 0,
                    onClick = { viewModel.setNavbarIndex(0) },
                    label = { Text(text = "Home") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Home, "Home") }
                )
                NavigationBarItem(
                    selected = navbarIndex == 1,
                    onClick = { viewModel.setNavbarIndex(1) },
                    label = { Text(text = "History") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Refresh, "History") }
                )
                NavigationBarItem(
                    selected = navbarIndex == 2,
                    onClick = { viewModel.setNavbarIndex(2) },
                    label = { Text(text = "Maps") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Place, "Maps") }
                )
                NavigationBarItem(
                    selected = navbarIndex == 3,
                    onClick = { viewModel.setNavbarIndex(3) },
                    label = { Text(text = "Profile") },
                    colors = navigationBarItemColors,
                    icon = { Icon(Icons.Default.Person, "Profile") }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (navbarIndex) {
                0 -> Text(text = "Home")
                1 -> Text(text = "History")
                2 -> MapsPage()
                3 -> ProfilePage()
            }
        }
    }
}