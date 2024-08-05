package com.example.daggermvvm.view

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggermvvm.R
import com.example.daggermvvm.databinding.ActivityProfileBinding
import com.example.daggermvvm.view.adapter.BankAdapter
import com.example.daggermvvm.view.adapter.ContactAdapter
import com.example.daggermvvm.view.adapter.TransactionAdapter


class profileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profile = profileViewModel
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels*0.65
        binding.txtValTotal.text = profileViewModel.getTotal()
        binding.recyclerviewBank.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterBank = BankAdapter(screenWidth,this.applicationContext!!, profileViewModel.getMyBanks())
        binding.recyclerviewBank.adapter = adapterBank
        binding.recyclerviewContact.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterContact =
            ContactAdapter(this.applicationContext!!, profileViewModel.getMyContact())
        binding.recyclerviewContact.adapter = adapterContact
        binding.recyclerviewTranc.layoutManager = LinearLayoutManager(this)
        val adapterTranction =
            TransactionAdapter(this.applicationContext!!, profileViewModel.getMyList())
        binding.recyclerviewTranc.adapter = adapterTranction
    }

}