package com.example.m2p.utile

import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class LayoutBackgroundTarget(private val layout: ConstraintLayout) : CustomTarget<Drawable>() {
    override fun onResourceReady(@NonNull resource: Drawable, transition: Transition<in Drawable>?) {
        layout.background = resource
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        layout.background = placeholder
    }
}