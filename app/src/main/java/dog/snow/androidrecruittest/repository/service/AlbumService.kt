package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawAlbum
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AlbumService {

    @Headers("User-agent: Cool app")
    @GET("albums/{id}")
    fun getAlbum(@Path("id") id : Int) : Observable<RawAlbum>

    companion object {
        fun create(): AlbumService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(AlbumService::class.java)
        }
    }
}