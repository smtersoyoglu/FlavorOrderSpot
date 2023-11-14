package com.sametersoyoglu.flavororderspot.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sametersoyoglu.flavororderspot.data.entity.Foods
import com.sametersoyoglu.flavororderspot.databinding.ItemFoodsBinding

class FoodsAdapter (var mContext: Context, var foodsList: List<Foods>) : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>(){

    inner class FoodsViewHolder(var binding: ItemFoodsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}