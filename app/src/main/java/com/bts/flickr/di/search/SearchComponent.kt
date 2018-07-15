package com.bts.flickr.di.search

import com.bts.flickr.ui.search.SearchActivity
import dagger.Subcomponent

@Subcomponent(modules = [(SearchModule::class)])
@SearchScope
interface SearchComponent {

    fun inject(activity: SearchActivity): SearchActivity
}