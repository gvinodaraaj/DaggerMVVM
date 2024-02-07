package com.example.daggermvvm.view
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.daggermvvm.data.Event
import com.example.daggermvvm.databinding.ActivityNewBinding
import java.util.Calendar

class NewEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener  {
    private lateinit var binding: ActivityNewBinding
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    var isNew:Boolean = false
    lateinit var dateEditText:EditText
    lateinit var viewModel: NewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        binding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.getBooleanExtra("isNew", true)) {
            isNew=true
        }else{
            isNew=false
            binding.eT.setText(intent.getStringExtra("name"))
            binding.eTMultiLine.setText(intent.getStringExtra("discription"))
            binding.editTextDate.setText(intent.getStringExtra("startDate"))
            binding.editTextDate2.setText(intent.getStringExtra("endDate"))
            binding.button.setText("Update")

        }
        binding.button.setOnClickListener {
            val str: String = binding.eT.text.toString()
            if (str.isNotEmpty()) {
                Toast.makeText(this, "You entered " + binding.eT.text.toString(), Toast.LENGTH_SHORT).show()
                viewModel= ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
                    NewViewModel::class.java)
               if(isNew)
               {
                   viewModel.insertEvent(Event(str, binding.eTMultiLine.text.toString(),binding.editTextDate.text.toString(),binding.editTextDate2.text.toString()))

               }else{

                   viewModel.updateEvent(Event(str, binding.eTMultiLine.text.toString(),binding.editTextDate.text.toString(),binding.editTextDate2.text.toString()))

               }

            }
            else {
                Toast.makeText(this, "Please enter Details", Toast.LENGTH_SHORT).show()
            }
        }
        binding.editTextDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            dateEditText=binding.editTextDate
            val datePickerDialog =
                DatePickerDialog(this@NewEventActivity, this@NewEventActivity, year, month,day)
            datePickerDialog.show()
        }

        binding.editTextDate2.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            dateEditText = binding.editTextDate2
            val datePickerDialog =
                DatePickerDialog(this@NewEventActivity, this@NewEventActivity, year, month, day)
            datePickerDialog.show()
        }
}


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month+1
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this@NewEventActivity, this@NewEventActivity, hour, minute,
            false)
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute

        dateEditText.setText("" + myYear +"/" + myMonth + "/" + myDay + "  " + myHour + ":" + myMinute)
    }
}