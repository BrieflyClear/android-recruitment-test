package dog.snow.androidrecruittest.repository.service

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.service.network.ConnectivityInterceptorImpl
import dog.snow.androidrecruittest.repository.service.network.TimeoutInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.io.IOException
import java.util.concurrent.TimeUnit


interface UserService {

    @Headers("User-agent: Cool app")
    @GET("/users/{id}")
    fun getUserAsync(@Path("id") id : Int) : Deferred<RawUser>

    companion object {
        operator fun invoke(
            connectivityInterceptorImpl: ConnectivityInterceptorImpl
        ): UserService {
            val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .addInterceptor(connectivityInterceptorImpl)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(TimeoutInterceptor())
                .build()

            val retrofit = Retrofit.Builder().client(client)
                .addConverterFactory(
                    GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(UserService::class.java)
        }
    }
}