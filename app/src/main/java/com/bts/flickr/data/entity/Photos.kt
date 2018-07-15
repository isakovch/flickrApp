package com.bts.flickr.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class Photos() : Parcelable {
    var page: Int? = null
    var pages: Int? = null
    var perpage: Int? = null
    var total: String? = null
    @SerializedName("photo")
    var photoList: ArrayList<Photo>? = null

    constructor(parcel: Parcel) : this() {
        page = parcel.readValue(Int::class.java.classLoader) as? Int
        pages = parcel.readValue(Int::class.java.classLoader) as? Int
        perpage = parcel.readValue(Int::class.java.classLoader) as? Int
        total = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(page)
        parcel.writeValue(pages)
        parcel.writeValue(perpage)
        parcel.writeString(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photos> {
        override fun createFromParcel(parcel: Parcel): Photos {
            return Photos(parcel)
        }

        override fun newArray(size: Int): Array<Photos?> {
            return arrayOfNulls(size)
        }
    }

}