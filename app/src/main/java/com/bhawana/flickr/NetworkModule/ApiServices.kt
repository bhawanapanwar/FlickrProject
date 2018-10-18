package com.bhawana.flickr.NetworkModule



import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/**
 * **  Created by home on 18-Oct-18.
 */
interface ApiServices {

    @Headers("Content-Type: application/json")
    @GET("?method=flickr.photos.search")
    fun getFlickrImage(@Query("api_key") api_key: String,
                       @Query("text") text: String,
                       @Query("format") format: String,
                       @Query("nojsoncallback") nojsoncallback: Int,
                       @Query("page") page: Int,
                       @Query("per_page") per_page: Int): Call<JsonElement>
}