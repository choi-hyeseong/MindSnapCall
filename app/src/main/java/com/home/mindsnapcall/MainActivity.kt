package com.home.mindsnapcall

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.UnderlineSpan
import android.text.util.Linkify
import com.home.mindsnapcall.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityMainBinding.inflate(layoutInflater)
        bind.button.setOnClickListener {
            startActivity(Intent("intent.IMAGE_GENERATE").apply {
                putExtra("PROMPT", bind.prompt.text.toString())
                putExtra("ARTSTYLE", bind.style.text.toString())
            })
        }

        bind.button2.setOnClickListener {
            val url = "intent://genimage?prompt=${bind.prompt.text}&artstyle=${bind.style.text}"
            val builder = SpannableStringBuilder(url).apply {
                setSpan(UnderlineSpan(), 0, url.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            bind.url.text = builder
            bind.url.linksClickable = true
            bind.url.isClickable = true
            bind.url.movementMethod = LinkMovementMethod.getInstance()
            bind.url.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }

        bind.url.movementMethod = LinkMovementMethod.getInstance()



        setContentView(bind.root)
    }
}