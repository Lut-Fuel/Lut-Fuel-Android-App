package id.lutfuel.app.ui.tripCalculator.calculatingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.data.model.request.CalculateCostRequest
import id.lutfuel.app.ui.destinations.TripDetailPageDestination

@Destination
@Composable
fun CalculatingPage(
    request: CalculateCostRequest,
    navigator: DestinationsNavigator,
    viewModel: CalculatingPageViewModel = hiltViewModel()
) {
    val state by viewModel.result.collectAsState()

    LaunchedEffect(true) {
        viewModel.calculateCost(request)
    }

    LaunchedEffect(key1 = state) {
        state?.let {
            navigator.popBackStack()
            navigator.navigate(TripDetailPageDestination(it))
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.calculating),
            contentDescription = "Calculating",
        )
        LinearProgressIndicator()
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Your trip cost is being calculated... get ready to hit the road!",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF3F3D56),

                textAlign = TextAlign.Center,
            )
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCalculatingPage() {
//    LutFuelTheme {
//        CalculatingPage(
//            CalculateCostRequest(
//            1.1, 1.1, 1.1, 1.1, 1.1, 1, false, null
//        )
//        )
//
//    }
//}