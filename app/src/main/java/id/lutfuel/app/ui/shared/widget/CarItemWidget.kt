package id.lutfuel.app.ui.shared.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.lutfuel.app.R
import id.lutfuel.app.core.theme.LutFuelColor
import id.lutfuel.app.core.theme.LutFuelTheme

@Composable
fun CarItemWidget(
    fuelType: String,
    name: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                spotColor = Color(0x14000463),
                ambientColor = Color(0x14000463)
            )
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
            .padding(top = 4.dp, bottom = 4.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.car),
                contentDescription = "Car",
                tint = LutFuelColor.primaryDefault,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF2A2A25),

                    )
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFCCFFDD), shape = RoundedCornerShape(size = 12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = fuelType,
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF006622),

                        )
                )
            }
        }
    }
}

@Preview
@Composable
fun CarItemWidgetPreview() {
    LutFuelTheme {
        CarItemWidget(
            fuelType = "Pertalite",
            name = "Toyota Avanza"
        )
    }
}