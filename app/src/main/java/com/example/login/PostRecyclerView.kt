package com.example.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.model.Post

class PostRecyclerView(private val posts:List<Post>):RecyclerView.Adapter<PostRecyclerView.ViewPostHolder>() {


    class ViewPostHolder(view:View):RecyclerView.ViewHolder(view){
        val body = view.findViewById<TextView>(R.id.textViewDesc)
        val title = view.findViewById<TextView>(R.id.textViewTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPostHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)

        return ViewPostHolder(view)
    }

    override fun getItemCount(): Int {
        return  posts.size
    }

    override fun onBindViewHolder(holder: ViewPostHolder, position: Int) {
        holder.body.text = posts[position].body.toString()
        holder.title.text = posts[position].title.toString()

    }

}