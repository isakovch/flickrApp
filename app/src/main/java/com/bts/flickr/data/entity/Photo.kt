package com.bts.flickr.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class Photo() : Parcelable {

    var id: String? = null
    var owner: String? = null
    var secret: String? = null
    var server: String? = null
    var title: String? = null
    var farm: Int? = null
    @SerializedName("ispublic")
    var isPublic: Int? = null
    @SerializedName("isfriend")
    var isFriend: Int? = null
    @SerializedName("isfamily")
    var isFamily: Int? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        owner = parcel.readString()
        secret = parcel.readString()
        server = parcel.readString()
        title = parcel.readString()
        farm = parcel.readValue(Int::class.java.classLoader) as? Int
        isPublic = parcel.readValue(Int::class.java.classLoader) as? Int
        isFriend = parcel.readValue(Int::class.java.classLoader) as? Int
        isFamily = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(owner)
        parcel.writeString(secret)
        parcel.writeString(server)
        parcel.writeString(title)
        parcel.writeValue(farm)
        parcel.writeValue(isPublic)
        parcel.writeValue(isFriend)
        parcel.writeValue(isFamily)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }


}