package id.lutfuel.app.ui.tripCalculator.selectCar

import id.lutfuel.app.data.model.response.UsersCarListResponseItem
import id.lutfuel.app.ui.shared.state.AsyncState

data class SelectCarState(
    val selectedCarId: Int? = null,
    val usersCars: AsyncState<List<UsersCarListResponseItem>> = AsyncState.Loading(),
) {
    val canSubmit: Boolean
        get() = selectedCarId != null
}
