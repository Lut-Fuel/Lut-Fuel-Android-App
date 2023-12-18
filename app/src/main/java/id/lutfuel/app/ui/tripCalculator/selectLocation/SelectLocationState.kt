package id.lutfuel.app.ui.tripCalculator.selectLocation

import android.os.Parcelable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import id.lutfuel.app.data.model.response.GetRoutesResponseItem
import id.lutfuel.app.ui.shared.state.AsyncState
import kotlinx.parcelize.Parcelize


data class SelectLocationState(
    val from: SelectedLocation? = null,
    val destination: SelectedLocation? = null,
    val selectedRouteId: Int = 0,
    val routes: AsyncState<List<GetRoutesResponseItem>>? = null,
    val shownPolyline: List<LatLng>? = null,
    val cameraPositionState: CameraPositionState = CameraPositionState(
        position = CameraPosition.fromLatLngZoom(
            LatLng(-6.1753924, 106.8271528),
            10f
        )
    ),
) {
    val showFrom: Boolean
        get() = destination != null
    val showFooter: Boolean
        get() = (from != null) && (destination != null)

}

@Parcelize
data class SelectedLocation(
    val name: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable {
    val latLng: LatLng
        get() = LatLng(latitude, longitude)
}

