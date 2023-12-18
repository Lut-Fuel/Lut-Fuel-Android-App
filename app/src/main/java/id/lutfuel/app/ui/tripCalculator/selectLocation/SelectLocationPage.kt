package id.lutfuel.app.ui.tripCalculator.selectLocation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.ui.destinations.SearchLocationPageDestination
import id.lutfuel.app.ui.shared.state.AsyncState
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder
import id.lutfuel.app.ui.tripCalculator.searchLocation.SearchLocationPageResult
import id.lutfuel.app.ui.tripCalculator.searchLocation.SearchLocationPageType
import id.lutfuel.app.ui.tripCalculator.selectCar.SelectCarDestinationArguments

@OptIn(ExperimentalPermissionsApi::class)
@Composable
@Destination
fun SelectLocationPage(
    navigator: DestinationsNavigator,
    viewModel: SelectLocationViewModel = hiltViewModel(),
    searchResultRecipient: ResultRecipient<SearchLocationPageDestination, SearchLocationPageResult>
) {
    val locationPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(true) {
        locationPermissionState.launchPermissionRequest()
    }
    LaunchedEffect(locationPermissionState.status) {
        if (locationPermissionState.status.isGranted) {
            viewModel.getUsersLocation()
        }
    }

    searchResultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                val value = result.value
                viewModel.onLocationSelected(value.location, value.type)
            }
        }
    }


    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SelectLocationHeader(navigator, state)
        CustomGoogleMap(
            state,
            modifier = Modifier.weight(1f)
        )
        if (state.showFooter) {
            SelectLocationFooter(navigator, viewModel, state)
        }
    }
}

@Composable
fun CustomGoogleMap(
    state: SelectLocationState,
    modifier: Modifier = Modifier,
) {
    GoogleMap(
        cameraPositionState = state.cameraPositionState,
        modifier = modifier
    ) {
        state.from?.let {
            Marker(
                state = MarkerState(position = it.latLng)
            )
        }
        state.destination?.let {
            Marker(
                state = MarkerState(position = it.latLng)
            )
        }
        state.shownPolyline?.let {
            Polyline(points = it)
        }
    }
}

@Composable
fun SelectLocationHeader(
    navigator: DestinationsNavigator,
    state: SelectLocationState,
) {
    Box(
        modifier = Modifier
            .background(LutFuelColor.primaryDefault)
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Start Trip",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),
                )
            )
            if (state.showFrom) {
                Spacer(modifier = Modifier.height(10.dp))
                SelectLocationItem(
                    label = state.from?.name ?: "Where are you going from?"
                ) {
                    navigator.navigate(SearchLocationPageDestination(SearchLocationPageType.FROM))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            SelectLocationItem(
                label = state.destination?.name ?: "Where do you wanna go today?"
            ) {
                navigator.navigate(SearchLocationPageDestination(SearchLocationPageType.DESTINATION))
            }
        }
    }
}

@Composable
fun SelectLocationItem(
    label: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .background(LutFuelColor.primaryWhite, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .weight(1f)
        ) {
            Row {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3F3D56),

                        )
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.location),
            contentDescription = "Location",
            tint = Color.White
        )
    }
}

@Composable
fun SelectLocationFooter(
    navigator: DestinationsNavigator,
    viewModel: SelectLocationViewModel,
    state: SelectLocationState
) {
    Box(
        modifier = Modifier
            .background(LutFuelColor.primaryWhite)
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Route Choices",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF2A2A25),

                            )
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = state.from?.name ?: "Location",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF747474),
                                textAlign = TextAlign.Center,
                            )
                        )
                        Icon(
                            Icons.Default.ArrowForward,
                            tint = LutFuelColor.primaryDefault,
                            contentDescription = "Arrow Forward",
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(10.dp)
                        )
                        Text(
                            text = state.destination?.name ?: "Location",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF747474),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (state.routes != null) {
                Box(modifier = Modifier.height(100.dp)) {
                    DefaultAsyncStateBuilder(state = state.routes) { routes ->
                        Column {
                            routes.map { route ->
                                RoutingChoiceItem(
                                    routeName = route.name,
                                    time = route.durationString,
                                    distance = route.distanceString,
                                    isSelected = route.id == state.selectedRouteId,
                                    onClick = {
                                        viewModel.onRouteSelected(route.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clickable {
                        navigator.navigate(
                            id.lutfuel.app.ui.destinations.SelectCarPageDestination(
                                SelectCarDestinationArguments(
                                    fromLatitude = state.from?.latitude ?: 0.0,
                                    fromLongitude = state.from?.longitude ?: 0.0,
                                    destinationLatitude = state.destination?.latitude ?: 0.0,
                                    destinationLongitude = state.destination?.longitude ?: 0.0,
                                    distance = (state.routes!! as AsyncState.Success).data.first { it.id == state.selectedRouteId }.distance,
                                    tolls = (state.routes as AsyncState.Success).data.first { it.id == state.selectedRouteId }.id == 0,
                                )
                            )
                        )
                    }
                    .border(
                        width = 1.dp,
                        color = Color(0xFF3377FF),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.direct_up),
                        contentDescription = "Driving",
                        tint = LutFuelColor.primaryDefault,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Take this Route",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3377FF),
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun RoutingChoiceItem(
    routeName: String,
    time: String,
    distance: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x14000463),
                ambientColor = Color(0x14000463)
            )
            .then(
                if (isSelected) Modifier.border(
                    width = 1.dp,
                    color = LutFuelColor.primaryDefault,
                    shape = RoundedCornerShape(size = 5.dp)
                ) else Modifier
            )
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.driving),
                contentDescription = "Driving",
                tint = LutFuelColor.primaryDefault
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = routeName,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3F3D56),
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = time,
                style = TextStyle(
                    fontSize = 8.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF0029FE),
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = distance,
                style = TextStyle(
                    fontSize = 8.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF0029FE),
                )
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SelectLocationPagePreview() {
//    LutFuelTheme {
//        SelectLocationPage(EmptyDestinationsNavigator)
//    }
//}