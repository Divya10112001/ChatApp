package com.example.chatapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.activity.ChatActivity

class chatRVAdapter(val context: Context): RecyclerView.Adapter<chatRVAdapter.chatviewholder>() {
    inner class chatviewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cname = itemView.findViewById<TextView>(R.id.zelda)
        val smm = itemView.findViewById<TextView>(R.id.smm)
        val ctime = itemView.findViewById<TextView>(R.id.ctime)
        val liContent = itemView.findViewById<RelativeLayout>(R.id.liContent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatviewholder {
        val viewHolder=chatviewholder(LayoutInflater.from(context).inflate(R.layout.chatitem,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: chatviewholder, position: Int) {
        holder.cname.text="Zelda"
        holder.ctime.text="15 min ago"
        holder.smm.text="Something Extra..."
        holder.liContent.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return 10
        }
}
