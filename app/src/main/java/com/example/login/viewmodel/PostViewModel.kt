package com.example.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.model.Post
import com.example.login.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository ):ViewModel() {

    val posts = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun getPostsData(){
        try {
            isLoading.value = true
            viewModelScope.launch {
                posts.value = repository.getPosts()
                isLoading.value = false
            }
        }catch (e:Exception){
            Log.i("E=====>","==>$e")
            isLoading.value = false
        }

    }



}