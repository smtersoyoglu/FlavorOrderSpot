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
import com.sametersoyoglu.flavororderspot.ui.viewmodel.HomeViewModel
import com.sametersoyoglu.flavororderspot.util.gecisYap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodsAdapter (var mContext: Context, var foodsList: List<Foods>, var viewModel: HomeViewModel) : RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>(){

    inner class FoodsViewHolder(var binding: ItemFoodsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val binding: ItemFoodsBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_foods,parent,false)
        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = foodsList.get(position)
        val t = holder.binding
        var fav = false

        t.foodsObject = food

        //t.foodName.text = "${food.food_name}"
        //t.foodPrice.text = "${food.food_price} ₺"


        CoroutineScope(Dispatchers.Main).launch {
            val favoriteCount = getFavoriteCount(food.food_id)
            val isFavorite = favoriteCount > 0
            if (isFavorite) {
                t.favButton.setImageResource(R.drawable.heart_red)
            } else {
                t.favButton.setImageResource(R.drawable.heart_gray)
            }
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image_name}"
        Glide.with(mContext).load(url).into(t.foodImage)

        t.foodItemCardView.setOnClickListener {
            val action = HomeFragmentDirections.homeFragmentTofoodDetailsFragment(food)
            Navigation.gecisYap(it,action)
        }

        t.favButton.setOnClickListener {
            fav = !fav
            if (fav == false){
                t.favButton.setImageResource(R.drawable.heart_gray)
                viewModel.deleteFavorite(food.food_id)
            }
            else{
                viewModel.addFavoriteFoods(food.food_id,food.food_name,food.food_image_name,food.food_price)
                t.favButton.setImageResource(R.drawable.heart_red)
            }
        }
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

    suspend fun getFavoriteCount(food_id : Int) : Int {
        return viewModel.isFavorite(food_id)
    }
}