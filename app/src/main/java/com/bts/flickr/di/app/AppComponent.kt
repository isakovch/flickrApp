package com.bts.flickr.di.app

import com.bts.flickr.di.search.SearchComponent
import com.bts.flickr.di.search.SearchModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class)])
interface AppComponent {

    fun include(module: SearchModule): SearchComponent
}