package com.example.daggermvvm.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.R
import com.example.daggermvvm.data.BankDetails
import com.example.daggermvvm.data.TransactionList

class BankAdapter(private var allEvent: List<BankDetails>) : RecyclerView.Adapter<BankAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_bank, parent, false)
        return ViewHolder(view)
    }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textBankName.text = allEvent.get(position).bank_name
            holder.textBankId.text = allEvent.get(position).bank_id.toString()
            holder.textBalance.text = allEvent.get(position).amount.toString()
            holder.constraintLayout.setBackgroundColor(Color.RED)
        }

    override fun getItemCount(): Int {
        return allEvent.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textBankName: TextView = itemView.findViewById(R.id.txt_val_bank)
        val textBankId: TextView = itemView.findViewById(R.id.txt_bank_id_val)
        val textBalance: TextView = itemView.findViewById(R.id.txt_bank_total_val)
        val constraintLayout: ConstraintLayout =itemView.findViewById(R.id.lay_back)

    }
}








