package com.example.alcometerapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.alcometerapp.MainViewModel
import com.example.alcometerapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.lifecycleOwner = this;

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        binding.viewModel = viewModel;

        viewModel.hasProfile.observe(viewLifecycleOwner, Observer {
            if( it == true){
                binding.profileLayout.visibility = View.GONE;
                binding.editProfileLayout.visibility = View.VISIBLE;
            }
        })

        if(viewModel.gender.value == "man")
            binding.radioButtonM.isChecked = true;
        else
            binding.radioButtonW.isChecked = true;

        binding.genderRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = group.findViewById(checkedId)

            when (radio) {
                binding.radioButtonM -> {
                   viewModel.gender.value= "man"
                }
                binding.radioButtonW -> {
                    viewModel.gender.value= "woman"
                }
            }

        })

        val weightNumberPicker = binding.weightNumberPicker;
        weightNumberPicker.minValue = 20;
        weightNumberPicker.maxValue = 300;
        weightNumberPicker.value=70;

        val growthNumberPicker = binding.growthNumberPicker;
        growthNumberPicker.minValue = 50;
        growthNumberPicker.maxValue = 250;
        growthNumberPicker.value=150;

        binding.createProfileButton.setOnClickListener {
            binding.profileLayout.visibility=View.GONE
            binding.editProfileLayout.visibility=View.VISIBLE;
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}