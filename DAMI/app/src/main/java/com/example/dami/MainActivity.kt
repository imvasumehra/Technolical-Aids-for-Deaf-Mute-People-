package com.spearhead.dami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
//import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "",
                "",
                R.drawable.sliderm
            ),
            IntroSlide(
                "Speech to Text",
                "Speech to Text feature, to listen to what your loved ones have to say",
                R.drawable.slider1
            ),
            IntroSlide(
                "Text to Speech",
                "Express yourself in words with our Text to Speech feature\ncomes with built in emergency responses",
                R.drawable.slider2
            ),
            IntroSlide(
                "Emergency",
                "Find yourself in an Emergency situation?\nTake a breathe and call Emergency Services via our integrated features",
                R.drawable.slider3
            )
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setcurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setcurrentIndicator(position)
            }
        })
        buttonNext.setOnClickListener {
            if (introSliderViewPager.currentItem +  1 <introSliderAdapter.itemCount)
            {
                introSliderViewPager.currentItem += 1
            }
            else {
                Intent(applicationContext, AnotherActivity::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }
        textSkipIntro.setOnClickListener {
            Intent(applicationContext, AnotherActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
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
            indicatorsContainer.addView(indicators[i])
        }

    }

    private fun setcurrentIndicator(index: Int) {

        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index)
            {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else {
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
