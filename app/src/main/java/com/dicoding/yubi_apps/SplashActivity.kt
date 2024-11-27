package com.dicoding.yubi_apps

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.dicoding.yubi_apps.MainActivity
import com.dicoding.yubi_apps.R
import com.google.android.gms.ads.MobileAds

//@suppressLint("CustomSplashActivity")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Inisialisasi SDK Google Mobile Ads
        MobileAds.initialize(this) {
            Log.d("SplashActivity", "Google Mobile Ads SDK initialized.")
        }

        Handler(Looper.getMainLooper()).postDelayed({
            goToMainActivity()
        },3000L)
    }

    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}