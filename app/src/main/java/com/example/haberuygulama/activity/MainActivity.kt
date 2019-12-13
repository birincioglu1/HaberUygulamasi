package com.example.haberuygulama.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haberuygulama.R
import com.example.haberuygulama.adapter.NewsAdapter
import com.example.haberuygulama.models.ResponseModel
import com.example.haberuygulama.rests.APIInterface
import com.example.haberuygulama.rests.ApiClient
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val API_KEY:String="ae02673c06634031a1db6adcfa782b89"
    var sports="sports"
    var business="business"
    var health="health"
    var science="science"
    var technology="technology"
    var gelenVeri:ResponseModel?=null
    var gelenHaberler:List<ResponseModel.Articles>?=null
    var myAdapter:NewsAdapter?=null
    val manager=supportFragmentManager

    var mykategori:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        radio_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId==R.id.bilim)
            {
                istekOlustur(science)

            }

            if(checkedId==R.id.spor){
                istekOlustur(sports)

            }
            if(checkedId==R.id.business)
            {
                istekOlustur(business)
            }
            if (checkedId==R.id.health)
            {
                istekOlustur(health)
            }
            if (checkedId==R.id.technology)
            {
                istekOlustur(technology)
            }
        }


    }
    fun istekOlustur(kategori:String)
    {
        var istek=kategori
        var xxxxy=istek
        var deneme =Kategori
        deneme.kategori=xxxxy

        var apiInterface=ApiClient.client?.create(APIInterface::class.java)
        var apiCall=apiInterface?.getLatestNews(istek,API_KEY)

        apiCall?.enqueue(object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.e("BASARISIZ", ""+call.request()?.url()?.toString())

            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                Log.e("BASARILI", ""+call.request()?.url()?.toString())
                gelenVeri=response.body()
                gelenHaberler=gelenVeri?.articles
                myAdapter=NewsAdapter(this@MainActivity,gelenHaberler)
                recyclerView.adapter=myAdapter
                var mylayoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                recyclerView.layoutManager=mylayoutManager
                //supportActionBar?.setSubtitle("Toplam Haber:"+gelenHaberler?.size)




            }

        })


    }



}
