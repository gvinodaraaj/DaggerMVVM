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
import com.example.m2p.databinding.VideoLayoutBindingImpl


class DetailsViewActivity : AppCompatActivity() {
    lateinit var binding: VideoLayoutBindingImpl
    lateinit var viewModel: DetailsViewModel
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.video_layout)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding.details = viewModel

        val mediaController= MediaController(this)
        mediaController.setAnchorView(binding.videoA)
        val mediaControllerb= MediaController(this)
        mediaControllerb.setAnchorView(binding.videoB)

        val onlineUri:Uri=Uri.parse("https://sample-videos.com/video321/mp4/480/big_buck_bunny_480p_5mb.mp4")
        val offlineUri:Uri=Uri.parse("android.resourse://$packageName/${R.raw.test2}")
        binding.videoA.setMediaController(mediaController)
        binding.videoA.setVideoURI(onlineUri)
        binding.videoA.requestFocus()

        binding.videoB.setMediaController(mediaControllerb)
        binding.videoB.setVideoURI(offlineUri)

    }

}