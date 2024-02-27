package com.example.bettervicemusic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers(
        "X-RapidAPI-Key: f109b4b80bmsh7f639bd4848c85ap18de4fjsnb8f57798e98f" ,
        "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com"
    )
    @GET("search")
    fun getDate(@Query("q") query: String): Call<myData>

}