package id.lutfuel.app.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.ui.destinations.BottomNavBarPageDestination
import id.lutfuel.app.ui.destinations.OnboardingPageDestination

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreenPage(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(key1 = true) {
        if (Firebase.auth.currentUser != null) {
            navigator.popBackStack()
            navigator.navigate(BottomNavBarPageDestination())
        } else {
            navigator.popBackStack()
            navigator.navigate(OnboardingPageDestination())
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize(),

        ) {
        Image(
            painter = painterResource(id = R.drawable.lutfuel_logo),
            contentDescription = "LutFuel Logo",
            modifier = Modifier
                .width(190.dp)
        )
        Text(
            text = "LutFuel",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF3F3D56),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "Predict, plan, and pump\nwith confidence",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF747474),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPagePreview() {
    LutFuelTheme {
        SplashScreenPage(EmptyDestinationsNavigator)
    }
}