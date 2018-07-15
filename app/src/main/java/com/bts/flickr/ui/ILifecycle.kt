package com.bts.flickr.ui

interface ILifecycle<V> {

    fun bind(view: V)

    fun unbind()
}