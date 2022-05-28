package com.example.chatapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.chatRVAdapter

class MainActivity : AppCompatActivity() {
    lateinit var adapter: chatRVAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val rv= findViewById<RecyclerView>(R.id.rv)
        val call= findViewById<TextView>(R.id.call)
       layoutManager=LinearLayoutManager(this)
       adapter= chatRVAdapter(this)
       rv.adapter=adapter
       rv.layoutManager=layoutManager
       call.setOnClickListener {
           startActivity(Intent(this, MainActivity2::class.java))
       }
    }

    override fun onBackPressed() {
        onDestroy()
        super.onBackPressed()
    }
}