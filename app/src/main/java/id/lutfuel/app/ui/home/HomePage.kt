package id.lutfuel.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.data.model.response.Stats
import id.lutfuel.app.ui.destinations.HistoryPageDestination
import id.lutfuel.app.ui.shared.widget.CarItemWidget
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder
import id.lutfuel.app.ui.shared.widget.TripItemWidget

@Composable
@Destination
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state by viewModel.state.collectAsState()
    val currentUser = viewModel.firebaseAuth.currentUser!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),

        ) {
        Spacer(modifier = Modifier.height(12.dp))
        ProfileHeader(currentUser)
        DefaultAsyncStateBuilder(
            state = state,
            onRetry = { viewModel.getHome() }
        ) { data ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {

                    Spacer(modifier = Modifier.height(12.dp))
                    UserStat(data.stats)
                    SectionHeaders(label = "Cars") {}
                    data.cars.map { car ->
                        CarItemWidget(
                            fuelType = car.fuelType,
                            name = car.customName,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    SectionHeaders(label = "Latest Trip") {
                        navigator.navigate(HistoryPageDestination)
                    }
//                val currencyFormat = remember { NumberFormat.getCurrencyInstance(Locale.getDefault())}
                    data.history.map { trip ->
                        TripItemWidget(
                            carName = trip.carName,
                            from = trip.from,
                            to = trip.destination,
                            fuel = "${trip.fuelNeeded} Liters",
                            cost = "Rp ${trip.cost}"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    user: FirebaseUser
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hi, ${user.displayName}!",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )
            Text(
                text = "Welcome to Lutfuel",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3F3D56),
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        AsyncImage(
            model = user.photoUrl,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.dp, LutFuelColor.primaryDefault, CircleShape)
        )
    }
}

@Composable
private fun UserStat(
    stats: Stats
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LutFuelColor.primaryDefault, RoundedCornerShape(10.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow),
                        contentDescription = "Arrow",
                        tint = LutFuelColor.primaryWhite,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = stats.distanceTraveled.toString(),
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                    Text(
                        text = "Km",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.padding(start = 2.dp, top = 20.dp)
                    )
                }
                Text(
                    text = "distance travelled",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(61.dp)
                    .width(1.dp)
                    .background(LutFuelColor.primaryWhite)
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.radar),
                        contentDescription = "Arrow",
                        tint = LutFuelColor.primaryWhite,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = stats.fuelConsumed.toString(),
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                    Text(
                        text = "Liters",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.padding(start = 2.dp, top = 20.dp)
                    )
                }
                Text(
                    text = "fuel consumed",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }
    }
}

@Composable
private fun SectionHeaders(
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),

                )
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onClick) {
            Text(
                text = "See all",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF3377FF),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    LutFuelTheme {
        HomePage(navigator = EmptyDestinationsNavigator)
    }
}