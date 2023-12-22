package id.lutfuel.app.ui.carList.addCar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.ramcosta.composedestinations.result.ResultRecipient
import id.lutfuel.app.data.model.FuelType
import id.lutfuel.app.data.model.response.CarListResponseItem
import id.lutfuel.app.ui.destinations.SearchCarPageDestination

@Composable
@Destination
fun AddCarPage(
    navigator: DestinationsNavigator,
    viewModel: AddCarViewModel = hiltViewModel(),
    carResultRecipient: ResultRecipient<SearchCarPageDestination, CarListResponseItem>,
    resultNavigator: ResultBackNavigator<Boolean>
) {
    carResultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                viewModel.onSelectCar(result.value)
            }
        }
    }


    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        if (state.success) {
            resultNavigator.navigateBack(result = true)
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        var carCustomName by remember { mutableStateOf("") }
        Text(
            text = "Name",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),

                )
        )
        TextField(
            value = carCustomName,
            onValueChange = { carCustomName = it },
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Car Type",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),

                )
        )
        Button(onClick = { navigator.navigate(SearchCarPageDestination) }) {
            Text(state.selectedCarName ?: "Select Car")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Fuel Type",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),

                )
        )
        var expanded by remember { mutableStateOf(false) }
        var selectedFuelType by remember { mutableStateOf(FuelType.types.first().name) }
        Button(onClick = { expanded = true }) {
            Text(text = selectedFuelType)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            FuelType.types.forEach { fuelType ->
                DropdownMenuItem(text = { Text(fuelType.name) }, onClick = {
                    selectedFuelType = fuelType.name
                    expanded = false
                })

            }
        }
        Button(onClick = {
            viewModel.onSubmit(
                carCustomName,
                FuelType.types.first { fuelType -> fuelType.name == selectedFuelType }.id
            )
        }) {
            Text(text = "Add Car")
        }
    }
}
