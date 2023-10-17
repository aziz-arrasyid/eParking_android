package com.example.eparking

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("/api/tableParkir{id}")
    fun getTable(
        @Path("id") id: String,
    ): Call<TableResponse>
    @FormUrlEncoded
    @POST("/api/authenticate")
    fun authenticate (
        @Field("username") username: String,
        @Field("password")  password: String,
        ): Call<LoginResponse>
}
