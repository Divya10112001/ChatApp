package com.example.chatapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.chatapplication.R

class ShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        supportActionBar?.hide()
        val next = findViewById<TextView>(R.id.next)
        val skip = findViewById<TextView>(R.id.skip)
        next.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        skip.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}