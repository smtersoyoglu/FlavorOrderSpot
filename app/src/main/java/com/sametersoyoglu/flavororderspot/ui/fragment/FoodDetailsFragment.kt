package com.sametersoyoglu.flavororderspot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.databinding.FragmentFoodDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodDetailsFragment = this
        binding.foodCount = count

    }

}