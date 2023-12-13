package com.example.daggermvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.daggermvvm.R
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityLoginBinding
import com.example.daggermvvm.databinding.ActivityUtilBinding


class UtilActivity : AppCompatActivity(), UtilViewModel.Callback {
    lateinit var binding: ActivityUtilBinding
    lateinit var utilViewModel: UtilViewModel
    val data = ArrayList<Event>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_util)
        utilViewModel = ViewModelProvider(this).get(UtilViewModel::class.java)
        binding.util = utilViewModel
        utilViewModel.setCallback(this)
        val languages = resources.getStringArray(R.array.weight)
        if (binding.spinnerInput != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            binding.spinnerInput.adapter = adapter

            binding.spinnerInput.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@UtilActivity, languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        if (binding.spinnerOutput != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            binding.spinnerOutput.adapter = adapter

            binding.spinnerOutput.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@UtilActivity, languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

    override fun onFunctionTriggered() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("FromLogin", true)
        startActivity(intent)
        finish()
    }
}