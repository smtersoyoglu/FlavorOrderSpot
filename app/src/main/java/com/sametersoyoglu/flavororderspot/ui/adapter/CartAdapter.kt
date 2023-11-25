package com.sametersoyoglu.flavororderspot.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cart.food_image_name}"
        Glide.with(mContext).load(url).into(t.imageViewFood)

        t.minusButton.setOnClickListener {

        }

        t.plusButton.setOnClickListener {

        }

        t.closeButton.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }

}