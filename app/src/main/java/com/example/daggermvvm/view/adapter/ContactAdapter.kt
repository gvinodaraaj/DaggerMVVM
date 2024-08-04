package com.example.daggermvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggermvvm.R
import com.example.daggermvvm.data.ContactDetails
import com.example.daggermvvm.data.TransactionList

class ContactAdapter(private val context: Context, private var allEvent: List<ContactDetails>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_contact, parent, false)
        return ViewHolder(view)
    }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textName.text = allEvent.get(position).name.split(" ").get(0)

            Glide.with(context)
                .load("https://logopng.com.br/logos/google-37.png")
                .placeholder(R.drawable.profile_img)
                .error(R.drawable.profile_img)
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








