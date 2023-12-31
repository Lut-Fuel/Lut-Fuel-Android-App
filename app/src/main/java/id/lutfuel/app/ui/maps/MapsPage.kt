package id.lutfuel.app.ui.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun MapsPage(
    navigator: DestinationsNavigator
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize()
    )
}