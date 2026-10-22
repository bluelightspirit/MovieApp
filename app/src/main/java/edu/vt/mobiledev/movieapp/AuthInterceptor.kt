package edu.vt.mobiledev.movieapp
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer YOUR_TMDB_READ_ACCESS_TOKEN")
            .build()
        return chain.proceed(request)
    }
}