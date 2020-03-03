package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.repository.model.RawAlbum
import dog.snow.androidrecruittest.repository.service.network.ConnectivityInterceptorImpl
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface AlbumService {

    //@Headers("User-agent: Cool app")
    @GET("/albums/{id}")
    fun getAlbum(@Path("id") id : Int) : Deferred<RawAlbum>

    companion object {
        operator fun invoke(
            connectivityInterceptorImpl: ConnectivityInterceptorImpl
        ): AlbumService {
            val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .addInterceptor(connectivityInterceptorImpl)
                .build()

            val retrofit = Retrofit.Builder().client(client)
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(AlbumService::class.java)
        }
    }
}