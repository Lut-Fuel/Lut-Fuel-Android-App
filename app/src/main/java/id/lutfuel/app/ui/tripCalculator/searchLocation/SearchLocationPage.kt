package id.lutfuel.app.ui.tripCalculator.searchLocation

import android.os.Parcelable
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.data.model.response.SearchLocationResponseItem
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder
import id.lutfuel.app.ui.tripCalculator.selectLocation.SelectLocationViewModel
import id.lutfuel.app.ui.tripCalculator.selectLocation.SelectedLocation
import kotlinx.parcelize.Parcelize

enum class SearchLocationPageType {
    FROM,
    DESTINATION
}

@Parcelize
data class SearchLocationPageResult(
    val type: SearchLocationPageType,
    val location: SelectedLocation
) : Parcelable

@Composable
@Destination
fun SearchLocationPage(
    type: SearchLocationPageType,
    viewModel: SearchLocationViewModel = hiltViewModel(),
    resultNavigator: ResultBackNavigator<SearchLocationPageResult>
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
            label = { Text("Search Location") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions {
                viewModel.searchLocation(query)
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        if (resultState != null) {
            DefaultAsyncStateBuilder(state = resultState!!) { locations ->
                LazyColumn {
                    items(locations) { location ->
                        LocationSearchResultItem(
                            location = location
                        ) {
                            resultNavigator.navigateBack(SearchLocationPageResult(
                                type = type,
                                location = SelectedLocation(
                                    name = location.name,
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )
                            ))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationSearchResultItem(
    location: SearchLocationResponseItem,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Icon(Icons.Default.LocationOn, contentDescription = "Location")
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = location.name)
            Text(
                text = location.address,
                color = LutFuelColor.secondaryText,
                fontSize = 10.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchLocationPagePreview() {
//    LutFuelTheme {
////        SearchLocationPage(SearchLocationPageType.DESTINATION, EmptyDestinationsNavigator)
//    }
//}