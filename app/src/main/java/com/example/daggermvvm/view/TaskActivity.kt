package com.example.daggermvvm.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.daggermvvm.R
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityTaskBinding


class TaskActivity : AppCompatActivity(), TaskViewModel.Callback {
    lateinit var binding: ActivityTaskBinding
    lateinit var taskViewModel: TaskViewModel
    val data = ArrayList<Event>()
    var calculate=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        binding.task = taskViewModel
        taskViewModel.setCallback(this)
        val languages = resources.getStringArray(R.array.calculatorSymbol)


        if (binding.spinnerOutput != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            binding.spinnerOutput.adapter = adapter

            binding.spinnerOutput.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    calculate=position
                    Toast.makeText(this@TaskActivity, languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

    override fun onFunctionTriggered(inPut: Double, inPut2: Double) {
        var inPutVal= binding.edTxtInput.text.toString().toDouble()
        var inPut2Val= binding.edTxtInput2.text.toString().toDouble()


        when (calculate) {
            0 -> {
                Toast.makeText(this@TaskActivity,"Add", Toast.LENGTH_SHORT).show()
                val value=inPutVal+inPut2Val
                binding.edTxtOutPut.setText(value.toString())
            }

            1 -> {
                Toast.makeText(this@TaskActivity,"Sub", Toast.LENGTH_SHORT).show()
                val value=inPutVal-inPut2Val
                binding.edTxtOutPut.setText(value.toString())
            }

            2 -> {
                 Toast.makeText(this@TaskActivity,"Divide", Toast.LENGTH_SHORT).show()
                val value=inPutVal/inPut2Val
                binding.edTxtOutPut.setText(value.toString())
            }
            3 -> {
                Toast.makeText(this@TaskActivity,"multiplication", Toast.LENGTH_SHORT).show()
                val value=inPutVal*inPut2Val
                binding.edTxtOutPut.setText(value.toString())
            }
        }
    }
}