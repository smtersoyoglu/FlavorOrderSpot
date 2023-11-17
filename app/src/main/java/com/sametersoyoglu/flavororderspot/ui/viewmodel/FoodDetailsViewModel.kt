package com.sametersoyoglu.flavororderspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel(){

    fun addToCart (foodName: String, foodImageName: String, foodPrice: Int, foodOrderQuantity: Int, userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addToCart(foodName,foodImageName,foodPrice,foodOrderQuantity,userName)
        }
    }
}