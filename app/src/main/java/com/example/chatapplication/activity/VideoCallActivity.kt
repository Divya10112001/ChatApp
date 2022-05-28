package com.example.chatapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.chatapplication.R

class VideoCallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_video_call)
        val callback= findViewById<ImageView>(R.id.callback)
        callback.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}