package com.sametersoyoglu.flavororderspot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.databinding.FragmentFavoriteBinding
import com.sametersoyoglu.flavororderspot.ui.adapter.FavoriteFoodsAdapter
import com.sametersoyoglu.flavororderspot.ui.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModeli bağlama - onCreate içersinde olur bu işlem
        val tempViewModel : FavoriteViewModel by viewModels() // gecici bir viewmodele atayıp ordan bizim viewmodelimize bağlarız.
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_favorite,container,false)

        viewModel.favoriteFoodList.observe(viewLifecycleOwner)  {
            val favoriteFoodsListAdapter = FavoriteFoodsAdapter(requireContext(),it,viewModel)
            //binding.favoriteRecyclerView.adapter = favoriteFoodsListAdapter
            binding.favAdapter = favoriteFoodsListAdapter // Databinding
        }

        return binding.root
    }

}