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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.databinding.FragmentCartBinding
import com.sametersoyoglu.flavororderspot.ui.adapter.CartAdapter
import com.sametersoyoglu.flavororderspot.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private var foodList : List<CartItem> = listOf()

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
        binding.cartFragment = this

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadCart("sametersoyoglu")

        val recyclerView: RecyclerView = binding.cartRecyclerView

        // LinearLayoutManager oluşturup RecyclerView'e ayarlamak
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cartFoodList.observe(viewLifecycleOwner) { cartfoods ->
            cartfoods?.let {
                val cartAdapter = CartAdapter(requireContext(),it,viewModel)
                binding.cartRecyclerView.adapter = cartAdapter
                foodList = it
            }
        }
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.totalPriceText.text = "₺${it}"
        }

    }
    fun orderButton() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Siparişi Onaylıyor musunuz?")
        builder.setPositiveButton("Evet") { dialog, which ->
            dialog.dismiss()
            showCongratsDialog()
            // Sipariş onaylandığında siparişleri sil
            deleteFoodFromCart()

        }
        builder.setNegativeButton("Hayır") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
    fun deleteFoodFromCart() {
        // foodList içindeki her bir öğeyi silebilirsiniz
        foodList.forEach { viewModel.deleteFoodFromCart(it.cart_food_id, it.username) }

        // Sepet listesini yeniden yükleyin
        viewModel.loadCart("sametersoyoglu")

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

