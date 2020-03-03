package dog.snow.androidrecruittest.repository.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.network.ConnectivityInterceptorImpl
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {

    //@Headers("User-agent: Cool app")
    @GET("/users/{id}")
    fun getUser(@Path("id") id : Int) : Deferred<RawUser>

    companion object {
        operator fun invoke(
            connectivityInterceptorImpl: ConnectivityInterceptorImpl
        ): UserService {
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

            return retrofit.create(UserService::class.java)
        }
    }
}