package id.lutfuel.app.ui.tripDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import id.lutfuel.app.data.model.response.TripDetailResponse

@Composable
@Destination
fun TripDetailPage(
    tripDetail: TripDetailResponse,
) {
//    TODO: Add trip detail page
    Column {
        Text(text = "Total Cost: ${tripDetail.totalCost}")
    }
}