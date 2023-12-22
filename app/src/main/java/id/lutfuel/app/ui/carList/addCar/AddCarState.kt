package id.lutfuel.app.ui.carList.addCar

data class AddCarState(
    val selectedCarId: Int? = null,
    val selectedCarName: String? = null,
    val success: Boolean = false,
) {
    val isCarSelected: Boolean
        get() = selectedCarId != null && selectedCarName != null
}
