package ru.korolevss.core_impl.network

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder
            .addHeader("Authorization", "Bearer 7ff22dc8-e94b-45b8-a91a-ba59c44bb584")

        return chain.proceed(requestBuilder.build())
    }

}