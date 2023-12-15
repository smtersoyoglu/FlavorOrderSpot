package com.sametersoyoglu.flavororderspot.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel(){
    fun addToCart(food_name : String, food_image_name : String, food_price : Int,
                          food_order_quantity : Int, username : String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)
        }
    }

}