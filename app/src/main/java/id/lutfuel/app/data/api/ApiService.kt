package id.lutfuel.app.data.api

import id.lutfuel.app.data.model.response.PostItem
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostItem>
}