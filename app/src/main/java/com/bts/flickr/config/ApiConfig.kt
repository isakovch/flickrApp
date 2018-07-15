package com.bts.flickr.config

object ApiConfig {

    const val REST_ENDPOINT: String = "services/rest/"

    const val METHOD_INTERESTINGNESS: String = "flickr.interestingness.getList"
    const val METHOD_SEARCH: String = "flickr.photos.search"

    const val IMAGE_CONSTRUCTION_URL: String = "https://farm%1s.staticflickr.com/%2s/%3s_%4s.jpg"
}