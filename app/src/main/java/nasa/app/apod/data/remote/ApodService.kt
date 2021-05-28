package nasa.app.apod.data.remote

import nasa.app.apod.data.entities.ApodData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {

    @GET("apod")
    suspend fun getApodData(@Query("api_key") apiKey:String) : Response<ApodData>


}