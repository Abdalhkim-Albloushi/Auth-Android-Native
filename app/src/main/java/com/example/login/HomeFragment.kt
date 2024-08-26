package com.example.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.databinding.FragmentHomeBinding
import com.example.login.repository.PostRepository
import com.example.login.viewmodel.PostViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var postRecyclerView: PostRecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        arguments?.let { args ->
            val email = args.getString("email")
//         binding.takeEmail.text = "$email"
//            Toast.makeText(context, "Email: $email", Toast.LENGTH_LONG).show()
        }
        viewModel = PostViewModel(PostRepository())

        binding.reView.layoutManager = LinearLayoutManager(this.context)
        binding.reView.setHasFixedSize(true)
        viewModel.getPostsData()
        viewModel.posts.observe(viewLifecycleOwner){
            postRecyclerView=PostRecyclerView(it)
            binding.reView.adapter = postRecyclerView

        }


        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar2.visibility = View.VISIBLE
                binding.reView.visibility = View.GONE

            }else{
                binding.progressBar2.visibility = View.GONE
                binding.reView.visibility = View.VISIBLE
            }
        }







        return binding.root
    }

}