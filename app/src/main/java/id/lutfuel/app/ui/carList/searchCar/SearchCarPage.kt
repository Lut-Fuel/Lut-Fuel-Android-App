package id.lutfuel.app.ui.carList.searchCar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import id.lutfuel.app.data.model.response.CarListResponseItem
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder


@Destination
@Composable
fun SearchCarPage(
    viewModel: SearchCarViewModel = hiltViewModel(),
    resultNavigator: ResultBackNavigator<CarListResponseItem>
) {
    var query by remember { mutableStateOf("") }
    val resultState by viewModel.result.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search car") },
            leadingIcon = { Icon(Icons.Default.PlayArrow, contentDescription = "Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions {
                viewModel.searchCar(query)
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        if (resultState != null) {
            DefaultAsyncStateBuilder(state = resultState!!) { cars ->
                LazyColumn {
                    items(cars) { car ->
                        SearchCarResultItem(car) {
                            resultNavigator.navigateBack(car)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchCarResultItem(
    car: CarListResponseItem,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Icon(Icons.Default.PlayArrow, contentDescription = "Location")
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = car.carName)

    }
}