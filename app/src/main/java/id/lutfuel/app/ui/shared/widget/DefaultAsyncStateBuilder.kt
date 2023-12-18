package id.lutfuel.app.ui.shared.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.lutfuel.app.core.theme.LutFuelTheme
import id.lutfuel.app.ui.shared.state.AsyncState

@Composable
fun <T> DefaultAsyncStateBuilder(
    state: AsyncState<T>,
    onRetry: (() -> Unit)? = null,
    successBuilder: @Composable (data: T) -> Unit,
) {
    when (state) {
        is AsyncState.Error -> {
            val data = (state as AsyncState.Error<T>).exception
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = data.message ?: "Unknown error",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (onRetry != null)
                        Button(onClick = onRetry) {
                            Text(text = "Retry")
                        }
                }
            }
        }

        is AsyncState.Loading -> {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AsyncState.Success -> {
            val data = (state as AsyncState.Success<T>).data
            successBuilder(data)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultAsyncStateBuilderPreview() {
    LutFuelTheme {
        DefaultAsyncStateBuilder<String>(
            state = AsyncState.Loading(),
            successBuilder = { data ->
                Text(text = data)
            }
        )
    }
}