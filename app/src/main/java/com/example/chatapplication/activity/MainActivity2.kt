package com.example.chatapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.callRVAdapter

class MainActivity2 : AppCompatActivity() {
    lateinit var adapter: callRVAdapter
    lateinit var layoutmanager:RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        val rv= findViewById<RecyclerView>(R.id.rv2)
        val chat = findViewById<TextView>(R.id.chat2)
        layoutmanager=LinearLayoutManager(this)
        adapter= callRVAdapter(this)
        rv.adapter=adapter
        rv.layoutManager=layoutmanager
        chat.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}