package com.example.daggermvvm.view

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.R
import com.example.daggermvvm.data.ToDo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

    class ToDoCustomAdapter(private var allEvent: List<ToDo>) : RecyclerView.Adapter<ToDoCustomAdapter.ViewHolder>() {

    var onItemClick: ((ToDo) -> Unit)? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_card_view_design, parent, false)

        return ViewHolder(view)
    }

        override fun onBindViewHolder(holder: ToDoCustomAdapter.ViewHolder, position: Int) {
            // sets the image to the imageview from our itemHolder class
            holder.imageView.setText(allEvent.get(position).name)

            // sets the text to the textview from our itemHolder class
            holder.textView.text = allEvent.get(position).description

            // Set the CheckBox checked state programmatically
            holder.checkBox.isChecked = allEvent.get(position).isCompleted  // Set to 'true' if you want it checked, 'false' otherwise

            // You can also add an OnCheckedChangeListener if you want to perform some action when the CheckBox state changes
            holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                // Handle checkbox state change
                if (isChecked) {
                    // Checkbox is checked
                } else {
                    // Checkbox is unchecked
                }
            }
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(allEvent.get(position))
            }
        }

    override fun getItemCount(): Int {
        return allEvent.size
    }

    fun updateList(newList: List<ToDo>) {
        allEvent=newList
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: TextView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }
}








