package com.example.eparking

import com.google.gson.annotations.SerializedName

data class TableResponse(
    val no_plat: String,
    val status: String,
    val payment_type: String,
    val transport_id: Int,
    val jukir_id: Int,

    @SerializedName("transport")
    val jenisKendaraan: String, // Menambahkan atribut untuk relasi Transport
    val hargaParkir: String,

//    @SerializedName("jukir")
//    val jukir: Jukir // Menambahkan atribut untuk relasi Jukir
)

