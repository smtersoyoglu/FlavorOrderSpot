package com.sametersoyoglu.flavororderspot.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel(){


    fun addToCart(food_name : String, food_image_name : String, food_price : Int,
                          food_order_quantity : Int, username : String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)
        }
    }

    fun addFavoriteFoods(food_id : Int, food_name:String,food_image_name:String,food_price:Int) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addFavoriteFoods(food_id,food_name,food_image_name,food_price)
        }
    }

    fun deleteFavorite(food_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.deleteFavorite(food_id)
        }
    }
    /*
    fun isFavorite(food_id: Int): Int {
        // foodsRepository.isFavorite(food_id) metodunu çağır ve sonucu 'count' değişkenine ata
        val count = CoroutineScope(Dispatchers.IO).launch {
            foodsRepository.isFavorite(food_id)
        }

        // Eğer 'count' değişkeni 0'dan büyükse, bu yemek favoriler listesindedir
        return count > 0
    }
     */
}