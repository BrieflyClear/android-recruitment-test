package dog.snow.androidrecruittest.repository.service.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TimeoutInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        // try the request
        var response: Response = chain.proceed(request)
        var tryCount = 0
        while (!response.isSuccessful && tryCount < 3) {
            Log.d("intercept", "Request is not successful - $tryCount")
            tryCount++
            // retry the request
            response = chain.proceed(request)
        }
        // otherwise just pass the original response on
        return response
    }
}