package id.lutfuel.app.data.model.request

data class AddUserCarRequest(
    val customName: String,
    val carId: Int,
    val fuelId: Int
)
