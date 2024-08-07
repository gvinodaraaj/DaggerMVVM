package com.example.m2p.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m2p.R
import com.example.m2p.data.model.ContactDetails
import com.example.m2p.utile.StringFormater.firstString

class ContactAdapter(private val context: Context, private var allEvent: List<ContactDetails>) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textName.text = allEvent.get(position).name.firstString()
        Glide.with(context)
            .load(allEvent.get(position).image)
            .placeholder(R.drawable.profile_img)
            .error(R.drawable.transfer)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return allEvent.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textName: TextView = itemView.findViewById(R.id.txt_contact_name)
        val image: ImageView = itemView.findViewById(R.id.contact_image)
    }
}








