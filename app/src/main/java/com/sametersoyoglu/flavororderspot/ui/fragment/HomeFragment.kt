package com.sametersoyoglu.flavororderspot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.databinding.FragmentHomeBinding
import com.sametersoyoglu.flavororderspot.ui.adapter.FoodsAdapter
import com.sametersoyoglu.flavororderspot.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModeli bağlama - onCreate içersinde olur bu işlem
        val tempViewModel:HomeViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeFragment = this
        binding.toolbarTitle = "Hoşgeldiniz"

        //val layoutManager = GridLayoutManager(requireContext(), 2)
        //binding.recyclerView.layoutManager = layoutManager

        /*
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean { //harf girdikçe harf sildikce sonuç getirir.
                viewModel.search(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean { // arama iconuna bastığımız zaman sonuç getirir.
                viewModel.search(query)
                return true
            }
        })

         */

        // LiveData yapısı
        viewModel.foodsList.observe(viewLifecycleOwner) { foods ->
            foods?.let {
                val foodsListAdapter = FoodsAdapter(requireContext(),it,viewModel)
                binding.foodsListAdapter = foodsListAdapter}
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
    }
}