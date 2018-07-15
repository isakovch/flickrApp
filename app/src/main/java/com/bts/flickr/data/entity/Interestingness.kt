package com.bts.flickr.data.entity

import android.os.Parcel
import android.os.Parcelable

open class Interestingness() : Parcelable {
    var photos: Photos? = null
    var stat: String? = null

    constructor(parcel: Parcel) : this() {
        photos = parcel.readParcelable(Photos::class.java.classLoader)
        stat = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(photos, flags)
        parcel.writeString(stat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Interestingness> {
        override fun createFromParcel(parcel: Parcel): Interestingness {
            return Interestingness(parcel)
        }

        override fun newArray(size: Int): Array<Interestingness?> {
            return arrayOfNulls(size)
        }
    }
}