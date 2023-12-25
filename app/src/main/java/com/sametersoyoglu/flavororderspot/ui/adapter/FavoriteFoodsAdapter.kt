package com.sametersoyoglu.flavororderspot.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.data.entity.FavoriteFoods
import com.sametersoyoglu.flavororderspot.databinding.ItemFavoriteBinding
import com.sametersoyoglu.flavororderspot.ui.viewmodel.FavoriteViewModel

class FavoriteFoodsAdapter (var mContext: Context, var favoriteFoodsList : List<FavoriteFoods>, var viewModel: FavoriteViewModel) : RecyclerView.Adapter<FavoriteFoodsAdapter.FavoriteFoodsHolder>() {

    inner class FavoriteFoodsHolder (var binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteFoodsHolder {
        val binding : ItemFavoriteBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_favorite,parent,false)
        return FavoriteFoodsHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteFoodsHolder, position: Int) {
        val favFoods = favoriteFoodsList[position]
        val t = holder.binding

        t.favFoodsObject = favFoods

        t.favButton.setImageResource(R.drawable.heart_red)
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${favFoods.food_image_name}"
        Glide.with(mContext).load(url).into(t.imageViewFood)


        t.favButton.setOnClickListener{
            viewModel.deleteFavorite(favFoods.food_id)
        }
        t.favDelete.setOnClickListener {
            viewModel.deleteFavorite(favFoods.food_id)
        }
    }

    override fun getItemCount(): Int {
        return favoriteFoodsList.size
    }
}