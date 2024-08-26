package com.example.login

import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {

    val email = "abc@gmail.com"
    val password = "password"

lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)


        binding.textViewRegister.setOnClickListener {



            findNavController().navigate(R.id.registerFragment)
        }
        binding.buttonLogin.setOnClickListener {
            val emailInput  = binding.editEmail.text.toString()
            val passwordInput  = binding.editPassword.text.toString()

            binding.progressBar.visibility = View.VISIBLE
            binding.buttonLogin.visibility = View.GONE
            Handler().postDelayed(Runnable {

            if(password == passwordInput && email == emailInput){

               val action =  LoginFragmentDirections.actionLoginFragmentToHomeFragment(email ="${binding.editEmail.text}" )
                   Navigation.findNavController(binding.root).navigate(action)

            }else{
                Snackbar.make(binding.root, "Incorrect Email or Password", Snackbar.LENGTH_SHORT).show()
            }
                binding.buttonLogin.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE


            },3000)


        }
        return binding.root






    }





}