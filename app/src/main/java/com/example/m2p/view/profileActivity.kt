package com.example.m2p.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m2p.R
import com.example.m2p.databinding.ActivityProfileBinding
import com.example.m2p.utile.StringFormater.moneyString
import com.example.m2p.utile.createNotificationChannel
import com.example.m2p.utile.showNotification
import com.example.m2p.view.adapter.BankAdapter
import com.example.m2p.view.adapter.ContactAdapter
import com.example.m2p.view.adapter.TransactionAdapter


class profileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profile = profileViewModel
        createNotificationChannel(this)

        val displayMetrics = DisplayMetrics()
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)


        binding.txtValTotal.text = profileViewModel.getTotal().moneyString("$")
        binding.recyclerviewBank.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapterBank = BankAdapter(
            displayMetrics.widthPixels * 0.65,
            this.applicationContext!!,
            profileViewModel.getMyBanks()
        )
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
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, DetailsViewActivity::class.java)
            startActivity(intent)
           // showNotification(this)
        }
        binding.imageView.setOnClickListener {
            val intent = Intent(this, DetailsViewActivity::class.java)
            startActivity(intent)
        }
    }

}