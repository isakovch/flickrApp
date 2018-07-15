package com.bts.flickr.di.search

import com.bts.flickr.data.network.RetrofitManager
import com.bts.flickr.data.resource.ResourceManager
import com.bts.flickr.ui.search.SearchContract
import com.bts.flickr.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    internal fun providePresenter(resourceManager: ResourceManager, retrofitManager: RetrofitManager):
            SearchContract.Presenter = SearchPresenter(resourceManager, retrofitManager)
}
