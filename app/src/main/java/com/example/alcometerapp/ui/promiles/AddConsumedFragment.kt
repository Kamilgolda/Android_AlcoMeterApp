package com.example.alcometerapp.ui.promiles

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.alcometerapp.MainViewModel
import com.example.alcometerapp.databinding.FragmentAddConsumedBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddConsumedFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentAddConsumedBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddConsumedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.startDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this.context!!, { _, year, monthOfYear, dayOfMonth ->
                        val calendar: Calendar = Calendar.getInstance()
                        val hour = calendar.get(Calendar.HOUR)
                        val minute = calendar.get(Calendar.MINUTE)
                        val timePickerDialog = TimePickerDialog(this.context,
                            { _: TimePicker?, hourOfDay: Int, minute: Int ->
                                viewModel.startDate.value = Date(year-1900,monthOfYear,dayOfMonth,hourOfDay,minute)
                                binding.startDate.setText(year.toString() + "/" + monthOfYear.plus(1) + "/" + dayOfMonth + " " + hourOfDay + ":" + minute)
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
                DatePickerDialog(this.context!!, { _, year, monthOfYear, dayOfMonth ->
                    val calendar: Calendar = Calendar.getInstance()
                    val hour = calendar.get(Calendar.HOUR)
                    val minute = calendar.get(Calendar.MINUTE)
                    val timePickerDialog = TimePickerDialog(this.context, TimePickerDialog.OnTimeSetListener { _: TimePicker?, hourOfDay: Int, minute: Int ->
                        viewModel.endDate.value = Date(year-1900,monthOfYear,dayOfMonth,hourOfDay,minute)
                        binding.endDate.setText(year.toString() + "/" + monthOfYear.plus(1) + "/" + dayOfMonth + " " + hourOfDay + ":" + minute)
                    }, hour, minute,
                        DateFormat.is24HourFormat(this.context))
                    timePickerDialog.show()
                }, year, month, day)
            datePickerDialog.show()
        }

        binding.addConsumedButton.setOnClickListener{
            if(viewModel.strength.value != "" && viewModel.portion.value != "" && viewModel.quantity.value != "" && viewModel.startDate.value != null && viewModel.endDate.value != null){
                viewModel.insertConsumed()
                this.findNavController().navigate(
                    AddConsumedFragmentDirections.actionAddConsumedFragmentToNavigationPromiles())
            }
            else{
                Toast.makeText(context, "Uzupełnij wszystkie pola", Toast.LENGTH_LONG).show()
            }


        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}