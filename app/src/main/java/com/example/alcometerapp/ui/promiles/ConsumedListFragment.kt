package com.example.alcometerapp.ui.promiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.alcometerapp.MainViewModel
import com.example.alcometerapp.databinding.FragmentConsumedListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsumedListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentConsumedListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConsumedListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.viewModel = viewModel

        val adapter = ItemRecyclerViewAdapter()

        viewModel.consumedList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        val swipeGesture = object : SwipeGesture(context!!){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){
                    ItemTouchHelper.LEFT ->{
                        val item : Result = adapter.deleteItem(viewHolder.adapterPosition)
                        viewModel.deleteConsumed(item)
                    }
                    ItemTouchHelper.RIGHT ->{
                        val item : Result = adapter.deleteItem(viewHolder.adapterPosition)
                        viewModel.deleteConsumed(item)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.list)

        binding.list.adapter = adapter

        binding.addConsumedButton.setOnClickListener { view ->
            this.findNavController().navigate(
                ConsumedListFragmentDirections.actionNavigationPromilesToAddConsumedFragment())
        }

        binding.checkButton.setOnClickListener {
            this.findNavController().navigate(
                ConsumedListFragmentDirections.actionNavigationPromilesToCheckFragment())
        }

        return root
    }
}