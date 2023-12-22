package id.lutfuel.app.ui.carList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.ui.destinations.AddCarPageDestination
import id.lutfuel.app.ui.shared.widget.CarItemWidget
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun UserCarListPage(
    navigator: DestinationsNavigator,
    viewModel: UserCarListViewModel = hiltViewModel(),
    addCarResultRecipient: ResultRecipient<AddCarPageDestination, Boolean>
) {
    val state by viewModel.state.collectAsState()

    addCarResultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {
            }

            is NavResult.Value -> {
                viewModel.getUsersCars()
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = LutFuelColor.primaryDefault,
                onClick = { navigator.navigate(AddCarPageDestination) }
            ) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LutFuelColor.primaryDefault)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Car List",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),

                        )
                )
            }

            DefaultAsyncStateBuilder(state) { cars ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(cars) { car ->
                        CarItemWidget(fuelType = car.fuelType, name = car.customName)
                    }
                }
            }


        }

    }
}

//@Preview
//@Composable
//fun UserCarListPagePreview() {
//    LutFuelTheme {
//        UserCarListPage(EmptyDestinationsNavigator)
//    }
//}