package id.lutfuel.app.ui.tripCalculator.calculatingScreen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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

//    TODO: Add calculating page
    CircularProgressIndicator()
}