package com.sametersoyoglu.flavororderspot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.databinding.FragmentHomeBinding
import com.sametersoyoglu.flavororderspot.ui.viewmodel.HomeViewModel


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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean { //harf girdikçe harf sildikce sonuç getirir.
                viewModel.sear(newText)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean { // arama iconuna bastığımız zaman sonuç getirir.
                viewModel.search(query)
                return true
            }
        })

        // LiveData yapısı
        viewModel.taskList.observe(viewLifecycleOwner) {
            val taskListAdapter = TaskListAdapter(requireContext(),it,viewModel)
            binding.taskListAdapter = taskListAdapter
        }

    }
}