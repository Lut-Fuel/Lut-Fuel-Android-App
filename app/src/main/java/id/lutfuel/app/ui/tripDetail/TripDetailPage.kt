package id.lutfuel.app.ui.tripDetail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.data.model.response.TripDetailResponse
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder

@Composable
@Destination
fun TripDetailPageLoad(
    tripId: Int,
    viewModel: TripDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.getTripDetail(tripId)
    }

    DefaultAsyncStateBuilder(state) {
        TripDetailPage(tripDetail = it)
    }
}


@Composable
@Destination
fun TripDetailPage(
    tripDetail: TripDetailResponse,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(LutFuelColor.primaryDefault)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Trip Detail ",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF),

                    )
            )
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Car Detail",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3F3D56),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(39.dp)
                        .height(39.dp)
                        .background(
                            color = Color(0x333377FF),
                            shape = RoundedCornerShape(size = 10.dp)
                        )

                        .padding(start = 7.5.dp, top = 7.5.dp, end = 7.5.dp, bottom = 7.5.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.car),
                        contentDescription = "Car",
                        tint = LutFuelColor.primaryDefault
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = tripDetail.carCustomName,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF2A2A25),
                        )
                    )
                    Text(
                        text = tripDetail.carName,
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF747474),

                            )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFCCFFDD),
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = tripDetail.fuelType,
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF006622),

                            )
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color(0x14000463),
                        ambientColor = Color(0x14000463)
                    )
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Column {
                    DetailItem(
                        icon = R.drawable.engine,
                        title = "Cylinder",
                        value = tripDetail.cyliner
                    )
                    DetailItem(
                        icon = R.drawable.piston,
                        title = "Engine HP",
                        value = tripDetail.engineVolume
                    )
                    DetailItem(
                        icon = R.drawable.horse,
                        title = "Horsepower",
                        value = tripDetail.power
                    )
                    DetailItem(
                        icon = R.drawable.weight,
                        title = "Weight",
                        value = tripDetail.weight
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Trip Detail",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3F3D56),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color(0x14000463),
                        ambientColor = Color(0x14000463)
                    )
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Column {
                    DetailItem(
                        icon = R.drawable.gas_station,
                        title = "Fuel Consumed",
                        value = "${String.format("%.2f", tripDetail.fuelNeeded)} L"
                    )
                    DetailItem(
                        icon = R.drawable.routingg,
                        title = "Distance",
                        value = "${tripDetail.distance} KM"
                    )
                    DetailItem(icon = R.drawable.direct, title = "From", value = tripDetail.from)
                    DetailItem(
                        icon = R.drawable.locationn,
                        title = "Weight",
                        value = tripDetail.destination
                    )
                    DetailItem(
                        icon = R.drawable.drivingg,
                        title = "Highway Toll",
                        value = tripDetail.tolls.toString()
                    )

                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Cost Detail",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3F3D56),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color(0x14000463),
                        ambientColor = Color(0x14000463)
                    )
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Column {
                    DetailItem(
                        icon = R.drawable.gas_station,
                        title = "Total Fuel Cost",
                        value = "Rp ${String.format("%.0f", tripDetail.fuelCost)}"
                    )
                    DetailItem(
                        icon = R.drawable.drivingg,
                        title = "Highway Toll Cost",
                        value = "Rp ${tripDetail.tollCost}"
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Summary",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF3F3D56),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Color(0x14000463),
                        ambientColor = Color(0x14000463)
                    )
                    .fillMaxWidth()
                    .background(
                        color = LutFuelColor.primaryDefault,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Image(painterResource(R.drawable.wallet), contentDescription = "hhh")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Total Cost",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            color = Color.White,
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Rp ${String.format("%.0f", tripDetail.totalCost)}",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            color = Color.White,
                        )
                    )
                }

            }
        }
    }
}

@Composable
fun DetailItem(
    @DrawableRes icon: Int,
    title: String,
    value: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Image(painterResource(icon), contentDescription = "hhh")
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF3F3D56),
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF3377FF),
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TripDetailPagePreview() {
    TripDetailPage(
        tripDetail = TripDetailResponse.dummy
    )
}