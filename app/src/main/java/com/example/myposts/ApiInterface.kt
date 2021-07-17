package com.example.myposts


import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<Posts>>

    @GET("posts/{postId}")
    fun getPost(@Path("postId")postId:Int):Call<Posts>

    @GET("posts/{postId}/comments")
   fun getComments(@Path("postId")id:Int):Call<List<Comments>>

}


