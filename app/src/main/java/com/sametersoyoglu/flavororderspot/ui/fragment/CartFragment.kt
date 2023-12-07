package com.sametersoyoglu.flavororderspot.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.databinding.FragmentCartBinding
import com.sametersoyoglu.flavororderspot.ui.adapter.FoodsAdapter
import com.sametersoyoglu.flavororderspot.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModeli bağlama - onCreate içersinde olur bu işlem
        val tempViewModel : CartViewModel by viewModels() // gecici bir viewmodele atayıp ordan bizim viewmodelimize bağlarız.
        viewModel = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartFragment= this

        /*
        viewModel.cartFoodList.observe(viewLifecycleOwner) { cartfoods ->
            cartfoods?.let {
                val cartAdapter = FoodsAdapter(requireContext(),it,viewModel)
                binding.recyclerView.adapter = cartAdapter }
            //binding.foodsListAdapter = foodsListAdapter}
        }

         */


    }

    fun deleteFoods() {

    }

    private fun showCongratsDialog() {
        val congratsDialogBuilder = AlertDialog.Builder(context)
        congratsDialogBuilder.setTitle("Tebrikler")
        congratsDialogBuilder.setMessage("Sipariş verdiniz!")

        val congratsDialog = congratsDialogBuilder.create()
        congratsDialog.show()

        val handler = Handler()
        val runnable = Runnable {
            congratsDialog.dismiss()
        }
        handler.postDelayed(runnable, 3000)
    }
}