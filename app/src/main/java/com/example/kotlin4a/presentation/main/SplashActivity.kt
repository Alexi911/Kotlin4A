package com.example.kotlin4a.presentation.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin4a.R

class SplashActivity : AppCompatActivity()
{
    private val SPLASH_TIME_OUT: Long = 4000

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
                {
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }


}