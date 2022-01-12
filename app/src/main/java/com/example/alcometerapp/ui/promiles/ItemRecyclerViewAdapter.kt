package com.example.alcometerapp.ui.promiles

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.alcometerapp.MainViewModel
import com.example.alcometerapp.R
import dagger.hilt.android.AndroidEntryPoint

class ItemRecyclerViewAdapter: RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        mListener = listener
    }

    fun deleteItem(i: Int) : Result{
        data.drop(i);
        notifyDataSetChanged()
        return data.get(i)
    }

    var data =  listOf<Result>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val consumedInfo: TextView = itemView.findViewById(R.id.consumed_info)
        val consumedDates: TextView = itemView.findViewById(R.id.consumed_dates)

        fun bind(item: Result) {
            val res = itemView.context.resources
            consumedInfo.text = item.quantity.toString() + "x " + item.portion.toString() + "ml " + item.strength.toString() + "% alkoholu"
            consumedDates.text = item.startDate.date.toString() + "/" + (item.startDate.month+1).toString() + "/" + item.startDate.year.toString() + " " + item.startDate.hours.toString() + ":" + item.startDate.minutes.toString() + "-" + item.endDate.hours.toString() + ":" + item.endDate.minutes.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.consumed_list_item, parent, false)
                return ViewHolder(view)
            }
        }
    }

}