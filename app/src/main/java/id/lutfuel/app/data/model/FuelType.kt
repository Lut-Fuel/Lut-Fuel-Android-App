package id.lutfuel.app.data.model

data class FuelType(
    val id: Int,
    val name: String,
) {
    companion object {
        val types = listOf(
            FuelType(1, "Biosolar"),
            FuelType(2, "Dexlite"),
            FuelType(3, "Pertalite"),
            FuelType(4, "Pertamax"),
            FuelType(5, "Pertamax Turbo"),
            FuelType(6, "Pertamax Green"),
            FuelType(7, "Shell V-Power Diesel"),
            FuelType(8, "Shell Super"),
            FuelType(9, "Shell V-Power"),
            FuelType(10, "Shell V-Power Nitro+"),
            FuelType(11, "BP Diesel"),
            FuelType(12, "BP 92"),
            FuelType(13, "BP Ultimate"),
            FuelType(14, "Revvo 90"),
            FuelType(15, "Revvo 92"),
            FuelType(16, "Revvo 95"),
        )
    }
}

