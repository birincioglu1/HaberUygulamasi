package com.example.haberuygulama.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.haberuygulama.R
import com.example.haberuygulama.models.ResponseModel
import com.example.haberuygulama.rests.APIInterface
import com.example.haberuygulama.rests.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    val API_KEY:String="ae02673c06634031a1db6adcfa782b89"
    var gelenVeri: ResponseModel?=null
    var gelenHaberler:List<ResponseModel.Articles>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var filtre=Kategori
        var kat=filtre.kategori

        var apiInterface= ApiClient.client?.create(APIInterface::class.java)
        var apiCall=apiInterface?.getLatestNews(kat,API_KEY)
        apiCall?.enqueue(object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.e("BASARISIZ", ""+call.request()?.url()?.toString())
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                gelenVeri=response.body()
                gelenHaberler=gelenVeri?.articles

                var secilen=intent.extras?.get("secilen") as Int
                Log.e("BASARISIZ", ""+secilen)
                var sira=secilen
                tvDetayBaslik.text=gelenVeri?.articles?.get(sira)?.title.toString()
                Picasso.get().load(gelenVeri?.articles?.get(sira)?.urlToImage).resize(450,150).into(app_bar_image)
                tvDetayIcerik.text=gelenVeri?.articles?.get(sira)?.content.toString()

            }

        })


    }

}
