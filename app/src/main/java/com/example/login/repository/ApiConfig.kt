package com.example.login.repository

import com.example.login.model.Post
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiConfig {

    @GET("posts")
    suspend fun getPosts():List<Post>
}

class RetrofitCall {

    val retro by lazy {
        Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(MoshiConverterFactory.create()).build()
    }

    val api:ApiConfig by lazy {
        retro.create(ApiConfig::class.java)
    }
}
