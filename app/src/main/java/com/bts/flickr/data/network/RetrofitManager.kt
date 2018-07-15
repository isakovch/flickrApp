package com.bts.flickr.data.network

import com.bts.flickr.config.ApiConfig
import com.bts.flickr.data.entity.Interestingness
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitManager {

    @GET(ApiConfig.REST_ENDPOINT)
    fun getList(@Query("method") method: String,
                @Query("api_key") apiKey: String,
                @Query("per_page") perPage: Int,
                @Query("format") format: String,
                @Query("nojsoncallback") callback: Int): Single<Response<Interestingness>>

    @GET(ApiConfig.REST_ENDPOINT)
    fun getImagesByTag(@Query("method") method: String,
                       @Query("api_key") apiKey: String,
                       @Query("tags") query: String,
                       @Query("per_page") perPage: Int,
                       @Query("format") format: String,
                       @Query("nojsoncallback") callback: Int): Single<Response<Interestingness>>

}