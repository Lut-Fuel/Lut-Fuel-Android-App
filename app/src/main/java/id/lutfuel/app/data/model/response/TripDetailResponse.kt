package id.lutfuel.app.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TripDetailResponse(

    @field:SerializedName("engineVolume")
    val engineVolume: String,

    @field:SerializedName("distance")
    val distance: Double,

    @field:SerializedName("carName")
    val carName: String,

    @field:SerializedName("destination")
    val destination: String,

    @field:SerializedName("weight")
    val weight: String,

    @field:SerializedName("carCustomName")
    val carCustomName: String,

    @field:SerializedName("cyliner")
    val cyliner: String,

    @field:SerializedName("tolls")
    val tolls: Boolean,

    @field:SerializedName("fuelNeeded")
    val fuelNeeded: Double,

    @field:SerializedName("tollCost")
    val tollCost: Int,

    @field:SerializedName("fuelType")
    val fuelType: String,

    @field:SerializedName("from")
    val from: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("power")
    val power: String,

    @field:SerializedName("totalCost")
    val totalCost: Int,

    @field:SerializedName("fuelCost")
    val fuelCost: Int
) : Parcelable
