package id.lutfuel.app.ui.tripCalculator.selectCar

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.data.model.request.CalculateCostRequest
import id.lutfuel.app.ui.destinations.CalculatingPageDestination
import id.lutfuel.app.ui.shared.widget.CarItemWidget
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectCarDestinationArguments(
    val fromLatitude: Double,
    val fromLongitude: Double,
    val destinationLatitude: Double,
    val destinationLongitude: Double,
    val distance: Double,
    val tolls: Boolean,
) : Parcelable

@Destination
@Composable
fun SelectCarPage(
    arguments: SelectCarDestinationArguments,
    navigator: DestinationsNavigator,
    viewModel: SelectCarViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(
                    LutFuelColor.primaryDefault,
                    RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
                )
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Select Car",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),

                    )
            )
        }
        DefaultAsyncStateBuilder(state = state.usersCars) {cars ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(cars) { car ->
                    CarItemWidget(fuelType = car.fuelType, name = car.customName) {
                        navigator.navigate(
                            CalculatingPageDestination(
                                CalculateCostRequest(
                                    fromLatitude = arguments.fromLatitude,
                                    fromLongitude = arguments.fromLongitude,
                                    destinationLatitude = arguments.destinationLatitude,
                                    destinationLongitude = arguments.destinationLongitude,
                                    distance = arguments.distance,
                                    tolls = arguments.tolls,
                                    userCarId = car.id
                                )
                            )
                        )
                    }
                }
            }
        }



    }
}

@Preview(showBackground = true)
@Composable
fun SelectCarPagePreview() {
    LutFuelTheme {
//        SelectCarPage(
//
//        )
    }
}