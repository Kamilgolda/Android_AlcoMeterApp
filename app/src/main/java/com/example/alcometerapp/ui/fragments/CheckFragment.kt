package com.example.alcometerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.alcometerapp.ui.viewmodel.MainViewModel
import com.example.alcometerapp.R
import com.example.alcometerapp.databinding.FragmentCheckBinding
import com.example.alcometerapp.database.Result
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CheckFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!
    private lateinit var lineChart : LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.viewModel = viewModel
        var list : List<Result>
        viewModel.consumedList.observe(viewLifecycleOwner, Observer {
            it?.let {
                list = it

                if(!list.isEmpty()){
                    setLineChartData(list.first())

                    val now = Date();
                    val endDate = list.first().endDate

                    val diff: Long = now.time - endDate.time
                    val seconds = diff / 1000
                    val minutes = seconds / 60
                    val hours = minutes / 60
                    binding.promiles.text = calculatePromiles(list.first(), viewModel.profile.value!!.gender,viewModel.profile.value!!.weight, hours.toInt()).toString()

                    binding.sobering.text = (calculatePromiles(list.first(), viewModel.profile.value!!.gender,viewModel.profile.value!!.weight,hours.toInt())/0.2).toInt().toString() + "h"
                }

            }
        })

        lineChart = binding.chart

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun calculatePromiles(item : Result, gender:String, weight: Int, hours: Int): Float{
        var pAlcohol : Double = (((item.quantity*item.portion)*(item.strength/100.0))*0.8)-(hours*10)
        if(pAlcohol <= 0)
            return  0.0F
        val ratio : Double = if(gender == "man") 0.7 else 0.6
        return (pAlcohol/(ratio*weight)).toFloat()
    }

    fun setLineChartData(item: Result){

        val lineEntry = ArrayList<Entry>()

        var iterator = 0f

        do {
            val promiles = calculatePromiles(item, viewModel.profile.value!!.gender,viewModel.profile.value!!.weight,iterator.toInt())
            lineEntry.add(Entry(iterator, promiles))
            iterator++
        } while (promiles > 0f);

        val lineDataSet = LineDataSet(lineEntry,"promile")
        lineDataSet.color= resources.getColor(R.color.purple_700)
        //dataSet.setValueTextColor(...); // styling, ...
        lineDataSet.circleRadius = 2f
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = resources.getColor(R.color.purple_700)
        lineDataSet.fillAlpha = 30

        val data = LineData(lineDataSet)

        lineChart.data = data;
        lineChart.invalidate();
        lineChart.setBackgroundColor(resources.getColor(R.color.white))
        lineChart.animateXY(1000,1000)

    }
}