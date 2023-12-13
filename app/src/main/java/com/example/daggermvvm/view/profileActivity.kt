package com.example.daggermvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.R
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityLoginBinding
import com.example.daggermvvm.databinding.ActivityProfileBinding
import com.example.daggermvvm.databinding.ActivityUtilBinding


class profileActivity : AppCompatActivity(), ProfileViewModel.Callback {
    lateinit var binding: ActivityProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    val data = ArrayList<Event>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profile = profileViewModel
        profileViewModel.setCallback(this)
        val languages = resources.getStringArray(R.array.flower)
        val url=resources.getStringArray(R.array.flower)
        // getting the recyclerview by its id

        // this creates a vertical layout Manager
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter()

        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter
    }

    override fun onFunctionTriggered() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("FromList", true)
        startActivity(intent)
        finish()
    }
}