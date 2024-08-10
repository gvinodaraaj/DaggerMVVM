package com.example.m2p.view

import android.media.MediaController2
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.MediaController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.m2p.R
import com.example.m2p.databinding.ActivityViewBinding


class DetailsViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewBinding
    lateinit var viewModel: DetailsViewModel
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding.details = viewModel

        val mediaController= MediaController(this)
        mediaController.setAnchorView(binding.videoView)

        val onlineUri:Uri=Uri.parse("https://file-examples.com/storage/fe44eeb9cb66ab8ce934f14/2017/04/file_example_MP4_480_1_5MG.mp4")
        val offlineUri:Uri=Uri.parse("android.resourse://$packageName/${R.raw.test}")
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(onlineUri)
        binding.videoView.requestFocus()

    }

}