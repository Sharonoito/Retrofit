package com.example.myposts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

 class CommentsAdapter (var commentsList: List<Comments>, var context: Context):RecyclerView.Adapter<CommentViewHolder>(){
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
         var  itemView=LayoutInflater.from(parent.context).inflate(R.layout.comments_list_item,parent,false)
         return CommentViewHolder(itemView)
     }

     override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
         var currentComments=commentsList.get(position)
         holder.tvName2.text=currentComments.name
         holder.tvEmail2.text=currentComments.email
         holder.tvBody2.text=currentComments.body

     }

     override fun getItemCount(): Int {
         return commentsList.size
     }
 }

class CommentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvName2=itemView.findViewById<TextView>(R.id.tvName2)
    var tvEmail2=itemView.findViewById<TextView>(R.id.tvEmail2)
    var tvBody2=itemView.findViewById<TextView>(R.id.tvBody2)

}