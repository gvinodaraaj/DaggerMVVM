package com.example.daggermvvm.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.R
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityLoginBinding
import com.example.daggermvvm.databinding.ActivityMainBinding
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    val data = ArrayList<Event>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.main = mainViewModel
        mainViewModel.currentNavigationEvent.observe(this, { event ->

            when (event) {
                NavigationEvents.Add -> {
                    val intent = Intent(this, UtilActivity::class.java)
                    intent.putExtra("FromMain", true)
                    startActivity(intent)
                }

                NavigationEvents.Profile -> {
                    val intent = Intent(this, profileActivity::class.java)
                    intent.putExtra("FromMain", true)
                    startActivity(intent)
                }

                NavigationEvents.Util -> {
                    val intent = Intent(this, UtilActivity::class.java)
                    intent.putExtra("FromMain", true)
                    startActivity(intent)
                }
            }
        })
    }


}