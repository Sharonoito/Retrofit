package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }
    fun getPosts(){
        var rvPosts=findViewById<RecyclerView>(R.id.rvPosts)
        val retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrofit.getPosts()
        request.enqueue(object:Callback<List<Posts>>{

            override fun onResponse(call: Call<List<Posts>>, response:
            Response<List<Posts>>) {
                if(response.isSuccessful){
                    var posts=response.body()!!
                  var myAdapter=postAdapter(posts,baseContext)
                    rvPosts.adapter=myAdapter
                    rvPosts.layoutManager=LinearLayoutManager(baseContext)
                    Toast.makeText(baseContext,"${posts !!.size}posts", Toast.LENGTH_LONG).show()
                     }
            }

//          override fun onResponse(call: Call<List<Post>>, response:
//            Response<List<Post>>) {
//                if(response.isSuccessful){
//                    var posts=response.body()
//            var postsList=PostsAdapter(postsList!!)
//            rvPosts.layoutManager=LinearLayoutManager(baseContext)

//
//
//

                    override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
            }

        })

        }
    }
