package com.dicoding.yubi_apps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {

    private lateinit var ButtonMulai: Button
    private lateinit var ButtonLewati: Button

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Solusi pintar untuk\nPertanian lebih baik",
                "Aplikasi ini dirancang untuk membantu petani\nmendeteksi hama pada tanaman umbi-umbian\nsecara cepat dan akurat, menggunakan teknologi\npembelajaran mesin.",
                R.drawable.onboarding1
            ),
            IntroSlide(
                "Deteksi hama dengan\nTeknologi",
                "Temukan hama yang menyerang tanaman\numbi-umbian Anda dan dapatkan informasi\nhama yang akurat dari aplikasi.",
                R.drawable.onboarding2
            ),
            IntroSlide(
                "Cepat dan mudah\ndigunakan",
                "Cukup unggah foto, dan biarkan teknologi\ncerdas kami mengidentifikasi hama dengan\ncepat.",
                R.drawable.onboarding3
            ),
            IntroSlide(
                "Dirancang untuk\nPetani Indonesia",
                "Mitra terbaik petani Indonesia untuk mengenali\nancaman pada tanaman dan meningkatkan\nketahanan pangan nasional",
                R.drawable.onboarding4
            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val introSliderViewPager = findViewById<ViewPager2>(R.id.introSliderViewPager)
        ButtonMulai = findViewById(R.id.ButtonMulai)
        ButtonLewati = findViewById(R.id.ButtonLewati)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        ButtonMulai.setOnClickListener {
            if(introSliderViewPager.currentItem +1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }else{
                Intent(applicationContext,AnotherActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        ButtonLewati.setOnClickListener {
            Intent(applicationContext,AnotherActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                    this?.layoutParams = layoutParams
            }
            val indicatorContainer = findViewById<LinearLayout>(R.id.indicatorContainer)
                indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val indicatorContainer = findViewById<LinearLayout>(R.id.indicatorContainer)
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView =indicatorContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}

