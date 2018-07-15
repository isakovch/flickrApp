package com.bts.flickr.di.app

import android.content.Context
import com.bts.flickr.data.resource.ResourceManager
import com.bts.flickr.data.repository.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    internal fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager(context)
    }

    @Singleton
    @Provides
    internal fun providePreferences(): PreferenceManager {
        return PreferenceManager(context)
    }
}