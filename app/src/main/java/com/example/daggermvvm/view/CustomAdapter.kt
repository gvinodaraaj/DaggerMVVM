package com.example.daggermvvm.view

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

    class CustomAdapter(private val allEvent: List<Event>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var onItemClick: ((Event) -> Unit)? = null

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setText(allEvent.get(position).name)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = allEvent.get(position).description
        totalRemainingDate(
            getDateString(allEvent.get(position).startDate),
            getDateString(allEvent.get(position).endDate),
            holder.tvTotal,
            holder.tvRemaining,
            holder.progressDate
        )
        holder.tvStart.text = dateConverter(getDateString(allEvent.get(position).startDate))
        holder.tvEnd.text = dateConverter(getDateString(allEvent.get(position).startDate))
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(allEvent.get(position))
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return allEvent.size
    }

    fun updateList(newList: List<Event>) {

        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: TextView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val tvStart: TextView = itemView.findViewById(R.id.tv_start)
        val tvEnd: TextView = itemView.findViewById(R.id.tv_end)
        val tvTotal: TextView = itemView.findViewById(R.id.tv_total)
        val tvRemaining: TextView = itemView.findViewById(R.id.tv_remaining)
        val progressDate: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun dateConverter(date: LocalDate): String {

    return date.toString()
}

@RequiresApi(Build.VERSION_CODES.O)
private fun totalRemainingDate(
    fromDate: LocalDate,
    toDate: LocalDate,
    tvTotal: TextView,
    tvRemaining: TextView,
    progressDate: ProgressBar
) {
    val totalDays = getDateDifference(fromDate, toDate)
    tvTotal.text = getDateDifference(fromDate, toDate).toString() + " days"
    tvRemaining.text = getTotalDays(fromDate, toDate, totalDays, progressDate).toString()


}

@RequiresApi(Build.VERSION_CODES.O)
fun getTotalDays(
    fromDate: LocalDate,
    toDate: LocalDate,
    totalDays: Long,
    progressDate: ProgressBar
): String {
    val cal1 = Calendar.getInstance()
    cal1.set(toDate.year, toDate.monthValue, toDate.dayOfMonth)
    val cal2 = Calendar.getInstance()
    cal2.set(fromDate.year, fromDate.monthValue, fromDate.dayOfMonth)
    val currentDate = Calendar.getInstance()
    val msDiff: Long = currentDate.timeInMillis - cal2.timeInMillis
    val daysDiff: Long = TimeUnit.MILLISECONDS.toDays(msDiff)
    if (daysDiff < 0) {
        progressDate.progress = 0
        return totalDays.toString()
    } else if (daysDiff > totalDays) {
        progressDate.progress = 100
        return "o days"
    } else {
        progressDate.progress = (100/( totalDays/daysDiff)).toInt()
        return daysDiff.toString() + " days"
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun getDateDifference(fromDate: LocalDate, toDate: LocalDate): Long {
    val cal1 = Calendar.getInstance()
    cal1.set(toDate.year, toDate.monthValue, toDate.dayOfMonth)
    val cal2 = Calendar.getInstance()
    cal2.set(fromDate.year, fromDate.monthValue, fromDate.dayOfMonth)
    val msDiff: Long = cal1.timeInMillis - cal2.timeInMillis
    val daysDiff: Long = TimeUnit.MILLISECONDS.toDays(msDiff)
    return daysDiff
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDaysDifference(fromDate: LocalDate?, toDate: LocalDate?): Int {
    if (fromDate?.let { getDate(it) } == null || toDate?.let { getDate(it) } == null) {
        return 0
    } else {
        val total = ((getDate(toDate).time - getDate(fromDate).time) / (1000 * 60 * 60 * 24))
        return total.toInt()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDate(lDate: LocalDate): Date {
    val localDateTime = LocalDateTime.now()
    val zonedDateTime = localDateTime.atZone(ZoneOffset.systemDefault())
    val instant = zonedDateTime.toInstant()
    val date = Date.from(instant)
    return date
}

@RequiresApi(Build.VERSION_CODES.O)
public fun getDateString(startDate: String): LocalDate {
    val dateSecond = "12/16/2020 12:00:00 AM"
    val secondFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH)
    return LocalDate.parse(dateSecond, secondFormatter)
}
private fun <P1, R1> (((P1) -> R1)?).invoke() {

}
