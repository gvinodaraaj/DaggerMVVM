package com.example.daggermvvm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggermvvm.R
import com.example.daggermvvm.data.TransactionList

class TransactionAdapter(private val context: Context, private var allEvent: List<TransactionList>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    var onItemClick: ((TransactionList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_card_view_design, parent, false)

        return ViewHolder(view)
    }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textName.text=allEvent.get(position).name
            holder.textTime.text = allEvent.get(position).time

            holder.textBank.text = allEvent.get(position).bank_name
            if(allEvent.get(position).type.equals("CREDIT")) {
                holder.textAmount.text = "+"+allEvent.get(position).amount+"USD"
            }
            else{
                holder.textAmount.text = "-"+allEvent.get(position).amount+"USD"
            }
            if(allEvent.get(position).status.equals("Completed"))
            {
                holder.textStatus.text="Completed"
                holder.imageStatus.background =ContextCompat.getDrawable(context, R.drawable.accepted)
            }

            Glide.with(context)
                .load("https://dummyimage.com/300x200/000/fff") // URL or resource ID
                .placeholder(R.drawable.profile_img) // Optional placeholder image
                .error(R.drawable.profile_img) // Optional error image
                .into(holder.imageProfile)

        }

    override fun getItemCount(): Int {
        return allEvent.size
    }

    fun updateList(newList: List<TransactionList>) {
        allEvent=newList
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textName: TextView = itemView.findViewById(R.id.txt_name)
        val textTime: TextView = itemView.findViewById(R.id.txt_time)
        val textAmount: TextView = itemView.findViewById(R.id.txt_amount)
        val textBank: TextView = itemView.findViewById(R.id.txt_bank_name)
        val textStatus: TextView = itemView.findViewById(R.id.txt_status)
        val imageStatus: ImageView = itemView.findViewById(R.id.img_status)
        val imageProfile: ImageView = itemView.findViewById(R.id.profile_img)
    }
}








