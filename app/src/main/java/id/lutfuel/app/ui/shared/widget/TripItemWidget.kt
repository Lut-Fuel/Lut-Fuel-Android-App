package id.lutfuel.app.ui.shared.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.core.theme.LutFuelTheme

@Composable
fun TripItemWidget(
    carName: String,
    from: String,
    to: String,
    fuel: String,
    cost: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                spotColor = Color(0x14000463),
                ambientColor = Color(0x14000463)
            )
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.routing),
                contentDescription = "Routing",
                tint = LutFuelColor.primaryDefault
            )
            Spacer(modifier = Modifier.weight(1f))
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = carName,
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF3F3D56),
                        textAlign = TextAlign.Center,
                    )
                )
                Row {
                    Text(
                        text = from,
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF747474),

                            textAlign = TextAlign.Center,
                        )
                    )
                    Icon(
                        Icons.Default.ArrowForward,
                        tint = LutFuelColor.primaryDefault,
                        contentDescription = "Arrow Forward",
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(10.dp)
                    )
                    Text(
                        text = to,
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF747474),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Column (
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = fuel,
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF3377FF),
                        textAlign = TextAlign.Right,
                    )
                )
                Text(
                    text = cost,
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF3377FF),
                        textAlign = TextAlign.Right,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripItemWidgetPreview() {
    LutFuelTheme {
        TripItemWidget(
            carName = "Avanza",
            from = "Jakarta",
            to = "Bandung",
            fuel = "10 Liters",
            cost = "Rp. 100.000"
        )
    }
}