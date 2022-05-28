package com.example.chatapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.chatapplication.R

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.hide()
        val chatback= findViewById<ImageView>(R.id.chatback)
        val phone = findViewById<ImageView>(R.id.phone234)
        phone.setOnClickListener {
            startActivity(Intent(this, VideoCallActivity::class.java))
        }
        chatback.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}