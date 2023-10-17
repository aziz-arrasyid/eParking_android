package com.example.eparking

import android.os.Parcel
import android.os.Parcelable

data class LoginResponse(
    val id: String,
    val username: String,
    val role: String,
) : Parcelable {
    // Implementasikan metode writeToParcel untuk menulis objek ke Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(username)
        parcel.writeString(role)
        // Tulis atribut-atribut lainnya ke Parcel
    }

    // Implementasikan metode describeContents
    override fun describeContents(): Int {
        return 0
    }

    // Implementasikan CREATOR
    companion object CREATOR : Parcelable.Creator<LoginResponse> {
        override fun createFromParcel(parcel: Parcel): LoginResponse {
            return LoginResponse(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponse?> {
            return arrayOfNulls(size)
        }
    }

    // Implementasikan secondary constructor yang digunakan oleh CREATOR
    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
        // Dan atribut-atribut lainnya
    )
}
