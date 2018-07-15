package com.bts.flickr.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class HttpClientBuilder {

    private val TIMEOUT_VAL: Long = 20

    fun init(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val origin = chain.request()

                    val request = origin.newBuilder()
                            .build()

                    val response = chain.proceed(request)

                    return@Interceptor response
                }).addInterceptor(interceptor)
                .connectTimeout(TIMEOUT_VAL, TimeUnit.SECONDS)
                .build()
    }
}