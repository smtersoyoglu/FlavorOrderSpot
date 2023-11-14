package com.sametersoyoglu.flavororderspot.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.data.entity.Foods
import com.sametersoyoglu.flavororderspot.databinding.ItemFoodsBinding
import com.sametersoyoglu.flavororderspot.ui.fragment.HomeFragmentDirections

class FoodsAdapter (var mContext: Context, var foodsList: List<Foods>) : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>(){

    inner class FoodsViewHolder(var binding: ItemFoodsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding: ItemFoodsBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_foods,parent,false)
        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = foodsList.get(position)
        val t = holder.binding

        t.foodName.text = "${food.food_name}"
        t.foodPrice.text = "${food.food_price} ₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image_name}"
        Glide.with(mContext).load(url).into(t.foodImage)

        t.foodItemCardView.setOnClickListener {
            val action = HomeFragmentDirections.homeFragmentTofoodDetailsFragment(food = food)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}