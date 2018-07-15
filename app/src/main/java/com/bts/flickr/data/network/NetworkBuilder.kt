package com.bts.flickr.data.network

import com.bts.flickr.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {

    fun create(client: OkHttpClient, rxAdapterFactory: RxJava2CallAdapterFactory, gsonAdapterFactory: GsonConverterFactory): RetrofitManager {
        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(gsonAdapterFactory)
                .addCallAdapterFactory(rxAdapterFactory)
                .client(client)
                .build()

        return builder.create(RetrofitManager::class.java)
    }

}