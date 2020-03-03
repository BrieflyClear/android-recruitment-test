package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawPhoto
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {

    @Headers("User-agent: Cool app")
    @GET("photos")
    fun getPhotos(@Query("_limit") limit : Int ? = 100) : Observable<List<RawPhoto>>

    companion object {
        fun create(): PhotoService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(PhotoService::class.java)
        }
    }
}