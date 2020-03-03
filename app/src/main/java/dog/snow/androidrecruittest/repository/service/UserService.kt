package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawUser
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {

    @Headers("User-agent: Cool app")
    @GET("users/{id}")
    fun getUser(@Path("id") id : Int) : Observable<RawUser>

    companion object {
        fun create(): UserService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(UserService::class.java)
        }
    }
}