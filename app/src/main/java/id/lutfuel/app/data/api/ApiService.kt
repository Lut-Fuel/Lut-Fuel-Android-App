package id.lutfuel.app.data.api

import id.lutfuel.app.data.model.request.AddUserCarRequest
import id.lutfuel.app.data.model.request.CalculateCostRequest
import id.lutfuel.app.data.model.response.BaseResponse
import id.lutfuel.app.data.model.response.CarListResponseItem
import id.lutfuel.app.data.model.response.GetRoutesResponseItem
import id.lutfuel.app.data.model.response.HistoryListResponseItem
import id.lutfuel.app.data.model.response.HomeResponse
import id.lutfuel.app.data.model.response.PostItem
import id.lutfuel.app.data.model.response.SearchLocationResponseItem
import id.lutfuel.app.data.model.response.TripDetailResponse
import id.lutfuel.app.data.model.response.UsersCarListResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostItem>

    @GET("home")
    suspend fun getHome(
        @Header("Authorization") token: String
    ): BaseResponse<HomeResponse>

    @GET("location/search")
    suspend fun searchLocation(
        @Header("Authorization") token: String,
        @Query("q") query: String,
    ): BaseResponse<List<SearchLocationResponseItem>>

    @GET("routes")
    suspend fun getRoutes(
        @Header("Authorization") token: String,
        @Query("fromLatitude") fromLatitude: Double,
        @Query("fromLongitude") fromLongitude: Double,
        @Query("destinationLatitude") destinationLatitude: Double,
        @Query("destinationLongitude") destinationLongitude: Double,
    ): BaseResponse<List<GetRoutesResponseItem>>

    @GET("users-car")
    suspend fun getUsersCars(
        @Header("Authorization") token: String
    ): BaseResponse<List<UsersCarListResponseItem>>

    @POST("users-car")
    suspend fun addUsersCars(
        @Header("Authorization") token: String,
        @Body request: AddUserCarRequest
    ): BaseResponse<UsersCarListResponseItem>

    @POST("calculate-cost")
    suspend fun calculateCost(
        @Header("Authorization") token: String,
        @Body request: CalculateCostRequest
    ): BaseResponse<TripDetailResponse>

    @GET("history/{id}")
    suspend fun historyDetail(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): BaseResponse<TripDetailResponse>

    @GET("cars/search")
    suspend fun searchCar(
        @Header("Authorization") token: String,
        @Query("q") query: String
    ): BaseResponse<List<CarListResponseItem>>

    @GET("history")
    suspend fun getHistories(
        @Header("Authorization") token: String
    ): BaseResponse<List<HistoryListResponseItem>>

    @GET("history/{id}")
    suspend fun getHistoryDetail(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): BaseResponse<TripDetailResponse>

}