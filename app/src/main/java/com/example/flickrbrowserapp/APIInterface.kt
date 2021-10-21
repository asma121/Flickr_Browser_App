package com.example.flickrbrowserapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {

    @GET("/services/rest/")
    fun getImageDetails(@Query("tags") tags:String ,
                        @Query("method") method:String ,
                        @Query("per_page") per_page:String ,
                        @Query("api_key") api_key:String ,
                        @Query("format") format:String,
                        @Query("nojsoncallback") nojsoncallback:String): Call<Data>

}