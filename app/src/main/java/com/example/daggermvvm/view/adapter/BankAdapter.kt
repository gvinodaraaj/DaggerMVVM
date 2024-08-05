package com.example.daggermvvm.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggermvvm.R
import com.example.daggermvvm.data.model.BankDetails
import com.example.daggermvvm.utile.LayoutBackgroundTarget
import com.example.daggermvvm.utile.StringFormater.moneyString

class BankAdapter(private val sWidth:Double,private val context: Context, private var allEvent: List<BankDetails>) :
    RecyclerView.Adapter<BankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_bank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textBankName.text = allEvent.get(position).bank_name
        holder.textBankId.text = allEvent.get(position).bank_id
        holder.textBalance.text = allEvent.get(position).amount.moneyString("$")

        val layoutParams = holder.constraintLayout.layoutParams
        layoutParams.width = sWidth.toInt()
        holder.constraintLayout.layoutParams = layoutParams
        Glide.with(context)
            .load(allEvent.get(position).bank_bg)
            .into(LayoutBackgroundTarget(holder.constraintLayout))
    }

    override fun getItemCount(): Int {
        return allEvent.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textBankName: TextView = itemView.findViewById(R.id.txt_val_bank)
        val textBankId: TextView = itemView.findViewById(R.id.txt_bank_id_val)
        val textBalance: TextView = itemView.findViewById(R.id.txt_bank_total_val)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.lay_back)
    }
}