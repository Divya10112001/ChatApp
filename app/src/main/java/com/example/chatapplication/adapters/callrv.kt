package com.example.chatapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.activity.VideoCallActivity

class callRVAdapter (val context: Context): RecyclerView.Adapter<callRVAdapter.callviewholder>() {
    inner class callviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val smm = itemView.findViewById<TextView>(R.id.username)
        val ctime = itemView.findViewById<TextView>(R.id.time2)
        val liContent = itemView.findViewById<RelativeLayout>(R.id.liContent2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): callviewholder {
        val viewHolder=callviewholder(LayoutInflater.from(context).inflate(R.layout.callitem,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: callviewholder, position: Int) {
      holder.liContent.setOnClickListener {
              val intent = Intent(context, VideoCallActivity::class.java)
              context.startActivity(intent)
   }
    }

    override fun getItemCount(): Int {
        return 10
    }
}
