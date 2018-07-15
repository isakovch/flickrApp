package com.bts.flickr.di.app

import com.bts.flickr.data.network.HttpClientBuilder
import com.bts.flickr.data.network.NetworkBuilder
import com.bts.flickr.data.network.RetrofitManager
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@Singleton
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideClient(): OkHttpClient {
        return HttpClientBuilder().init()
    }

    @Singleton
    @Provides
    internal fun provideAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    internal fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }


    @Singleton
    @Provides
    internal fun provideRetrofitApi(client: OkHttpClient, rxAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): RetrofitManager {
        return NetworkBuilder.create(client, rxAdapterFactory, gsonConverterFactory)
    }

}