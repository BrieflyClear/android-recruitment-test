package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.repository.model.RawPhoto
import dog.snow.androidrecruittest.repository.service.network.ConnectivityInterceptorImpl
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    //@Headers("User-agent: Cool app")
    @GET("/photos")
    fun getPhotosAsync(@Query("_limit") limit : Int ? = 100) : Deferred<List<RawPhoto>>

    companion object {
        operator fun invoke(
            connectivityInterceptorImpl: ConnectivityInterceptorImpl
        ): PhotoService {
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

            return retrofit.create(PhotoService::class.java)
        }
    }
}