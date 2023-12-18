package id.lutfuel.app.data.model.response

import com.google.gson.annotations.SerializedName

data class HomeResponse(

	@field:SerializedName("cars")
	val cars: List<CarsItem>,

	@field:SerializedName("stats")
	val stats: Stats,

	@field:SerializedName("history")
	val history: List<HistoryItem>
)

data class HistoryItem(

	@field:SerializedName("fuelNeeded")
	val fuelNeeded: Any,

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

data class Stats(

	@field:SerializedName("fuelConsumed")
	val fuelConsumed: Any,

	@field:SerializedName("distanceTraveled")
	val distanceTraveled: Any
)

data class CarsItem(

	@field:SerializedName("fuelType")
	val fuelType: String,

	@field:SerializedName("customName")
	val customName: String
)
