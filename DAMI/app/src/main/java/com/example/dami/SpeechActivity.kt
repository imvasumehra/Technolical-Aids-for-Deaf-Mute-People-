package com.spearhead.dami

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_speech.*
import java.util.*

class SpeechActivity : AppCompatActivity() {

    lateinit var mTTS:TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech)

        back.setOnClickListener {
            Intent(applicationContext, AnotherActivity::class.java).also {
                startActivity(it)
            }
        }

        buttonOne.setOnClickListener {
            textEt.setText("I want help!")
        }

        buttonTwo.setOnClickListener {
            textEt.setText("Call POLICE!")
        }

        buttonThree.setOnClickListener {
            textEt.setText("I have hearing and speaking disability.")
        }

        buttonFour.setOnClickListener {
            textEt.setText("Please help me with directions.")
        }

        buttonFive.setOnClickListener {
            textEt.setText("Call AMBULANCE!")
        }

        mTTS = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {status ->
            if (status != TextToSpeech.ERROR)
            {
                mTTS.language = Locale.ENGLISH
                mTTS.setSpeechRate(0.8F)
            }
        })

        speakBtn.setOnClickListener {

            val toSpeak = textEt.text.toString()
            if (toSpeak == "") {
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
            }

            else{
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
                mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
            }
        }

        stopBtn.setOnClickListener {

            if (mTTS.isSpeaking){
                mTTS.stop()
                //mTTS.shutdown()
            }
            else{
                Toast.makeText(this, "Not Speaking", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onPause() {

        if (mTTS.isSpeaking){
            mTTS.stop()
            //mTTS.shutdown()
        }
        super.onPause()
    }
}
