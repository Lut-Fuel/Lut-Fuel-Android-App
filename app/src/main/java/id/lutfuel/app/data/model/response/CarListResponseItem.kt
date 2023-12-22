package id.lutfuel.app.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarListResponseItem(
    val id: Int,
    val carName: String
) : Parcelable
