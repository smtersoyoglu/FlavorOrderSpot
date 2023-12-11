package com.sametersoyoglu.flavororderspot.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.databinding.ItemCartBinding
import com.sametersoyoglu.flavororderspot.ui.viewmodel.CartViewModel

class CartAdapter (var mContext: Context, var cartFoodList : List<CartItem>, var viewModel:CartViewModel) : RecyclerView.Adapter<CartAdapter.CartItemHolder>(){

    inner class CartItemHolder(var binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val binding: ItemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_cart,parent,false)
        return CartItemHolder(binding)
    }



    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {

        val cart = cartFoodList.get(position)
        val t = holder.binding

        t.foodNameTextView.text = cart.food_name
        t.foodPriceTextView.text = "₺${cart.food_price}"
        t.orderAmountText.text = cart.food_order_quantity.toString()

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cart.food_image_name}"
        Glide.with(mContext).load(url).into(t.imageViewFood)

        t.minusButton.setOnClickListener {
            if (cart.food_order_quantity > 1) {
                cart.food_order_quantity --
                t.orderAmountText.text = cart.food_order_quantity.toString()
            }
        }

        t.plusButton.setOnClickListener {

        }

        t.closeButton.setOnClickListener {
            val message = "${cart.food_name} sepetten çıkarılsın mı ?"
            Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                .setAction("EVET") {

                    viewModel.deleteFoodFromCart(cart.cart_food_id,cart.username)
                    //viewModel.loadCart("sametersoyoglu")
                }.show()
            //viewModel.deleteFoodFromCart(cart.cart_food_id,cart.username)
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }

}