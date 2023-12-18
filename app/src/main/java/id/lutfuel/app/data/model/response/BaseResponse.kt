package id.lutfuel.app.data.model.response

data class BaseResponse<T>(
    val message: String,
    val data: T
)
