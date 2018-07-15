package com.bts.flickr.ui.search

import com.bts.flickr.data.entity.Photo
import com.bts.flickr.ui.ILifecycle
import com.bts.flickr.ui.IProgressBar

interface SearchContract {

    interface View : IProgressBar {
        fun onSuccess(photoList: ArrayList<Photo>)

        fun onError(msg: String)
    }

    interface Presenter : ILifecycle<View> {
        fun loadData(method: String, apiKey: String)

        fun searchImageByTag(apiKey: String, tag: String)
    }
}