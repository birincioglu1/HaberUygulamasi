package com.example.haberuygulama.rests

import com.example.haberuygulama.models.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("top-headlines?country=tr")

   fun getLatestNews(@Query("category") kategori:String,@Query("apiKey") apiKey:String):Call<ResponseModel>
   fun getLatesTum(@Query("apiKey") apiKey:String):Call<ResponseModel>

}