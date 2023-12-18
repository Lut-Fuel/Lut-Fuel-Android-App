package id.lutfuel.app.ui.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun HistoryPage(
    navigator: DestinationsNavigator
) {
    Text(text = "History Page")
}