package id.lutfuel.app.ui.shared.state

sealed class AsyncState<out T> {
    data class Loading<out T>(val progress: Int? = null) : AsyncState<T>()
    data class Error<out T>(val exception: Throwable) : AsyncState<T>()
    data class Success<out T>(val data: T) : AsyncState<T>()
}