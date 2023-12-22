package id.lutfuel.app.ui.history

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.lutfuel.app.ui.destinations.TripDetailPageLoadDestination
import id.lutfuel.app.ui.shared.widget.DefaultAsyncStateBuilder
import id.lutfuel.app.ui.shared.widget.TripItemWidget

@Composable
@Destination
fun HistoryPage(
    navigator: DestinationsNavigator,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    DefaultAsyncStateBuilder(state) { histories ->
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            items(histories) { history ->
                TripItemWidget(
                    carName = history.carName,
                    from = history.from,
                    to = history.destination,
                    fuel = "${String.format("%.2f", history.fuelNeeded)} Liters",
                    cost = "Rp ${String.format("%.0f", history.cost)}"
                ) {
                    navigator.navigate(TripDetailPageLoadDestination(history.id))
                }
            }
        }

    }
}