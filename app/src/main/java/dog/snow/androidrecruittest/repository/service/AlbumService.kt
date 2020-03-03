package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface AlbumService {

    //@Headers("User-agent: Cool app")
    @GET("albums/{id}")
    fun getAlbum(@Path("id") id : Int) : Deferred<RawAlbum>

    companion object {
        operator fun invoke(): AlbumService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(AlbumService::class.java)
        }
    }
}