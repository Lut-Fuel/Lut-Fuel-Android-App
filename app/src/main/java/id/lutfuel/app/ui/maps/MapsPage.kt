package id.lutfuel.app.ui.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapsPage() {
    GoogleMap(
        modifier = Modifier.fillMaxSize()
    )
}