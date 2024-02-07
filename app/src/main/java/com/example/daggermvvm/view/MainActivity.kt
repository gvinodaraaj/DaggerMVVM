package com.example.daggermvvm.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.daggermvvm.R
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityLoginBinding
import com.example.daggermvvm.databinding.ActivityMainBinding
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    val data = ArrayList<Event>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.main = mainViewModel

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        val task = Event("task 1","check","","")
        data.add(task)
        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)
        /*mainViewModel.allEvent.observe(this, androidx.lifecycle.Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })*/
        // Setting the Adapter with the recyclerview
        binding.recyclerview.adapter = adapter
        adapter.onItemClick = {
            startActivity(Intent(this, NewEventActivity::class.java).apply {
                // you can add values(if any) to pass to the next class or avoid using `.apply`
                putExtra("isNew", false)
                putExtra("name", it.name)
                putExtra("discription", it.description)
                putExtra("startDate", it.startDate)
                putExtra("endDate", it.endDate)
            })
            // do something with your item
        }
        mainViewModel.currentNavigationEvent.observe(this) { event ->

            when (event) {
                NavigationEvents.Add -> {
                    val intent = Intent(this, NewEventActivity::class.java)
                    intent.putExtra("FromMain", true)
                    startActivity(intent)
                }

                NavigationEvents.Profile -> {
                    val intent = Intent(this, profileActivity::class.java)
                    intent.putExtra("FromMain", true)
                    startActivity(intent)
                }

                NavigationEvents.Util -> {
                    val intent = Intent(this, TaskActivity::class.java)
                    intent.putExtra("FromMain", true)
                    startActivity(intent)
                }
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addEntries(name: String, fromDate: LocalDate, toDate: LocalDate) {
        data.add(Event("vinod", name, fromDate.toString(), toDate.toString()))

    }
}