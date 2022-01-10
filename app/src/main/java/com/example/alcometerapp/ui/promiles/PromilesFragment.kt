package com.example.alcometerapp.ui.promiles

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.alcometerapp.databinding.FragmentPromilesBinding
import com.example.alcometerapp.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PromilesFragment : Fragment() {

    //private lateinit var dashboardViewModel: DashboardViewModel
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentPromilesBinding? = null

    var startDay = 0
    var startMonth: Int = 0
    var startYear: Int = 0
    var startHour: Int = 0
    var startMinute: Int = 0

    var endDay = 0
    var endMonth: Int = 0
    var endYear: Int = 0
    var endHour: Int = 0
    var endMinute: Int = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPromilesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.lifecycleOwner = this;
        binding.viewModel = viewModel;

        binding.startDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this.context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        startDay = dayOfMonth
                        startYear = year
                        startMonth = month+1
                        val calendar: Calendar = Calendar.getInstance()
                        val hour = calendar.get(Calendar.HOUR)
                        val minute = calendar.get(Calendar.MINUTE)
                        val timePickerDialog = TimePickerDialog(this.context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                            startHour = hourOfDay
                            startMinute = minute
                            binding.startDate.setText(startYear.toString() + "/" + startMonth + "/" +startDay + " " + startHour + ":"+ startMinute);
                        }, hour, minute,
                            DateFormat.is24HourFormat(this.context))
                        timePickerDialog.show()
                }, year, month, day)
            datePickerDialog.show()
        }


        binding.endDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this.context!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    endDay = dayOfMonth
                    endYear = year
                    endMonth = month+1
                    val calendar: Calendar = Calendar.getInstance()
                    val hour = calendar.get(Calendar.HOUR)
                    val minute = calendar.get(Calendar.MINUTE)
                    val timePickerDialog = TimePickerDialog(this.context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                        endHour = hourOfDay
                        endMinute = minute
                        binding.endDate.setText(endYear.toString() + "/" + endMonth + "/" +endDay + " " + endHour + ":"+ endMinute);
                    }, hour, minute,
                        DateFormat.is24HourFormat(this.context))
                    timePickerDialog.show()
                }, year, month, day)
            datePickerDialog.show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}