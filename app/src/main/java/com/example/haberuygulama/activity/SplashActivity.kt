package com.example.haberuygulama.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.example.haberuygulama.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {

        var logoDondurAnimasyon= AnimationUtils.loadAnimation(this,R.anim.logodondur)

        imgLogo.animation=logoDondurAnimasyon

        object : CountDownTimer(6000, 1000){

            override fun onFinish() {
                var intent= Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {

            }


        }.start()
        super.onResume()
    }
}
