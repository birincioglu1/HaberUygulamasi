package com.example.haberuygulama.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.haberuygulama.R
import com.example.haberuygulama.activity.DetailActivity
import com.example.haberuygulama.models.ResponseModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tek_satir_haber.view.*

class NewsAdapter(mActivity: AppCompatActivity, tumHaberler:List<ResponseModel.Articles>?):RecyclerView.Adapter<NewsAdapter.NewsViewholder>() {
    var gelentumhaberler=tumHaberler
    var myActivity=mActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        var inflater=LayoutInflater.from(parent.context)
        var tek_satir_view=inflater.inflate(R.layout.tek_satir_haber ,parent,false)
        return NewsViewholder(tek_satir_view)

    }

    override fun getItemCount(): Int {
        return gelentumhaberler!!.size
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        var oanOlusturulanSatir=gelentumhaberler?.get(position)
        holder.setData(oanOlusturulanSatir,position)



    }

    inner class NewsViewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var tekSatirHaber=itemView as CardView
        var haberListTitle=tekSatirHaber.tvListeBaslik
        var haberListImage=tekSatirHaber.imgListeResim
        var haberListTarih=tekSatirHaber.tvTarih
        var haberposition=0
        fun setData(oanOlusturulanSatir:ResponseModel.Articles?,position: Int){
             haberListTitle.text=oanOlusturulanSatir?.title
            haberListTarih.text=oanOlusturulanSatir?.publishedAt

            Picasso.get().load(oanOlusturulanSatir?.urlToImage).resize(450,150).into(haberListImage)
            haberposition=position


        }
        init {
            tekSatirHaber.setOnClickListener {
                var intent= Intent(myActivity,DetailActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                intent.putExtra("secilen",haberposition)
                intent.putExtra("kategori",haberposition)

                myActivity.startActivity(intent)



            }
        }


    }


}