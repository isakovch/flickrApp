package com.bts.flickr

import android.support.multidex.MultiDexApplication
import com.bts.flickr.di.app.*

class FlickrApp : MultiDexApplication() {

    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = initDagger()
    }

    private fun initDagger(): AppComponent {
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }
}