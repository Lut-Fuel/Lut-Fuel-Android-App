package id.lutfuel.app.data.model.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CalculateCostRequest(

    @field:SerializedName("fromLang")
    val fromLongitude: Double,

    @field:SerializedName("distance")
    val distance: Double,

    @field:SerializedName("fromLocation")
    val fromLocation: String,

    @field:SerializedName("fromLat")
    val fromLatitude: Double,

    @field:SerializedName("destination")
    val destination: String,

    @field:SerializedName("destinationLat")
    val destinationLatitude: Double,

    @field:SerializedName("destinationLang")
    val destinationLongitude: Double,

    @field:SerializedName("userCarId")
    val userCarId: Int? = null,

    @field:SerializedName("tolls")
    val tolls: Boolean,

    @field:SerializedName("newCar")
    val newCar: NewCar? = null
) : Parcelable

@Parcelize
data class NewCar(

    @field:SerializedName("saveCar")
    val saveCar: Boolean,

    @field:SerializedName("customName")
    val customName: String,

    @field:SerializedName("carId")
    val carId: Int,

    @field:SerializedName("fuelId")
    val fuelId: Int
) : Parcelable
