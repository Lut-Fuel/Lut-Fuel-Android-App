package id.lutfuel.app.data.model.response

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.PolyUtil

data class GetRoutesResponseItem(

    @field:SerializedName("duration")
    val duration: Int,

    @field:SerializedName("cost")
    val cost: Int,

    @field:SerializedName("distance")
    val distance: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("polyline")
    val encodedPolyline: List<String>
) {
    val durationString: String
        get() = "${duration / 60} hours ${duration % 60} minutes"

    val distanceString: String
        get() = "$distance km"

    val decodedPolyline: List<LatLng>
        get() = encodedPolyline.flatMap { PolyUtil.decode(it) }
}
