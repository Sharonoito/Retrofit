package com.example.myposts
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentActivity : AppCompatActivity() {
    var postId = 0
    lateinit var tvPostTitle: TextView
    lateinit var tvPostBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        postId=intent.getIntExtra("post_id",0)
        castViews()
        getPost()
        getComments()

    }
fun castViews(){
    tvPostTitle=findViewById(R.id.tvPostTitle)
    tvPostBody=findViewById(R.id.tvPostBody)
}
fun  getPost(){
    if (postId==0){
        Toast.makeText(baseContext,"Post not found",Toast.LENGTH_LONG).show()
        finish()
    }
    var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
    val request = apiClient.getPost(postId)
    request.enqueue(object : Callback<Posts>{
        override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
            if (response.isSuccessful){
                var posts=response.body()
                tvPostBody.text=posts?.body
                tvPostTitle.text=posts?.title

            }        }

        override fun onFailure(call: Call<Posts>, t: Throwable) {
            Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()

        }
    })
}
    fun getComments() {
        var rvComments = findViewById<RecyclerView>(R.id.rvComments)
        val retro = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = retro.getComments(postId)
        request.enqueue(object :Callback<List<Comments>>{

            override fun onResponse(call: Call<List<Comments>>, response:
            Response<List<Comments>>) {
                if (response.isSuccessful) {
                    var comments = response.body()!!
                    var myAdapter = CommentsAdapter(comments, baseContext)
                    rvComments.adapter = myAdapter
                    rvComments.layoutManager = LinearLayoutManager(baseContext)
                    Toast.makeText(baseContext, "${comments!!.size}comments", Toast.LENGTH_LONG)
                        .show()
                }
            }
                override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
            }

            })}
         }
