package id.lutfuel.app.data.model.response

import com.google.gson.annotations.SerializedName

data class UsersCarListResponseItem(

	@field:SerializedName("engineVolume")
	val engineVolume: String,

	@field:SerializedName("fuelType")
	val fuelType: String,

	@field:SerializedName("carName")
	val carName: String,

	@field:SerializedName("weight")
	val weight: String,

	@field:SerializedName("customName")
	val customName: String,

	@field:SerializedName("cylinder")
	val cylinder: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("power")
	val power: String
)
