package com.example.hw5_yelpclone

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpController {
    @GET("businesses/search")
    fun searchRestaraunts(
        @Header("Authorization") authHeader: String,
        @Query("term") termName: String,
        @Query("location") location: String) : Call<YelpData>

//    @GET("/businesses/{id}/reviews")
//    fun getReviews(
//        @Header("Authorization") authHeader: String,
//        @Path("id") id: String) : Call<YelpData>
}
