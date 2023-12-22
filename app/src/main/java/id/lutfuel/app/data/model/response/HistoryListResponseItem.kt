package id.lutfuel.app.data.model.response

import com.google.gson.annotations.SerializedName

data class HistoryListResponseItem(

    @field:SerializedName("fuelNeeded")
    val fuelNeeded: Double,

    @field:SerializedName("cost")
    val cost: Int,

    @field:SerializedName("carName")
    val carName: String,

    @field:SerializedName("destination")
    val destination: String,

    @field:SerializedName("from")
    val from: String,

    @field:SerializedName("id")
    val id: Int
)
