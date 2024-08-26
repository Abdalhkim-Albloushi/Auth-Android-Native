package com.example.login.repository

import android.util.Log
import com.example.login.model.Post
import kotlin.Exception

class PostRepository {
val retrofit = RetrofitCall()


    suspend fun getPosts():List<Post>{
    try {
        return retrofit.api.getPosts()
    }catch (e:Exception){
        Log.i("E=====>","==>$e")
    throw Exception()
    }

    }
}