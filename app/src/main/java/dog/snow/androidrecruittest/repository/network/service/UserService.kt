package dog.snow.androidrecruittest.repository.network.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.repository.model.RawUser
import dog.snow.androidrecruittest.repository.network.ConnectivityInterceptorImpl
import dog.snow.androidrecruittest.repository.network.TimeoutInterceptor
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface UserService {

    @Headers("User-agent: Cool app")
    @GET("/users/{id}")
    fun getUser(@Path("id") id : Int) : Observable<RawUser>

    companion object {
        operator fun invoke(): UserService {
            val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                })
                //.addInterceptor(connectivityInterceptorImpl)
                //.connectTimeout(10, TimeUnit.SECONDS)
                //.readTimeout(15, TimeUnit.SECONDS)
                //.addInterceptor(TimeoutInterceptor())
                .build()

            val retrofit = Retrofit.Builder().client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(UserService::class.java)
        }
    }
}