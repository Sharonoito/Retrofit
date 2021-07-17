package com.example.myposts
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class postAdapter(var postList:List<Posts>,var context: Context):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder{
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
      var currentPost=postList.get(position)

        holder.tvTitle.text=currentPost.title
        holder.tvBody.text=currentPost.body
//        holder.tvId.text=currentPost.id.toString()
        holder.cvPost.setOnClickListener {
            var intent =Intent(context,CommentActivity::class.java)
            intent.putExtra("post_id",currentPost.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return  postList.size
    }
}

class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvTitle=itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvBody)
    var cvPost=itemView.findViewById<CardView>(R.id.cvPost)

}
