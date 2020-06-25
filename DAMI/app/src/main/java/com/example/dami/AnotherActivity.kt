package com.spearhead.dami

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_another.*

class AnotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        r1c1.setOnClickListener {
            Intent(applicationContext, ListenActivity::class.java).also {
                startActivity(it)
            }
        }

        r1c2.setOnClickListener {
            Intent(applicationContext, SpeechActivity::class.java).also {
                startActivity(it)
            }
        }

        r2c1.setOnClickListener {
            val number = Uri.parse("tel:100")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(callIntent)

        }

        r2c2.setOnClickListener {
            val number = Uri.parse("tel:102")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(callIntent)
        }

        r3c1.setOnClickListener {
            val number = Uri.parse("tel:101")
            val callIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(callIntent)
        }

        r3c2.setOnClickListener {
            val launchIntent = packageManager.getLaunchIntentForPackage("in.cdac.ners.psa.mobile.android.national")
            if (launchIntent != null) {
                startActivity(launchIntent)//null pointer check in case package name was not found
            }
            else
            {
                val appPackageName = "in.cdac.ners.psa.mobile.android.national"
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                        )
                    )
                } catch (anfe: android.content.ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
            }
        }
    }
}
