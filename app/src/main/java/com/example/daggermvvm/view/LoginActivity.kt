package com.example.daggermvvm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.daggermvvm.R
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), LoginViewModel.Callback {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    val data = ArrayList<Event>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.user = loginViewModel
        loginViewModel.setCallback(this)

    }

    override fun onFunctionTriggered() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("FromLogin", true)
        startActivity(intent)
        finish()
    }
}