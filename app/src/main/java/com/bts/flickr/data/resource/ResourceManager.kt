package com.bts.flickr.data.resource

import android.content.Context

class ResourceManager(internal var context: Context) {

    fun getStringResource(resourceId: Int): String {
        return context.getString(resourceId)
    }
}