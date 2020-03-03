package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawPhoto
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {

    //@Headers("User-agent: Cool app")
    @GET("photos")
    fun getPhotos(@Query("_limit") limit : Int ? = 100) : Deferred<List<RawPhoto>>

    companion object {
        operator fun invoke(): PhotoService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(PhotoService::class.java)
        }
    }
}