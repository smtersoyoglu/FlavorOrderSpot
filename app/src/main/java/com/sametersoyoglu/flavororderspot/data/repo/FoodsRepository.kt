package com.sametersoyoglu.flavororderspot.data.repo

import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.entity.Foods

class FoodsRepository (var foodsDataSource: FoodsDataSource){

    suspend fun loadFoods() : List<Foods> = foodsDataSource.loadFoods()

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodOrderQuantity: Int, userName: String) = foodsDataSource.addToCart(foodName,foodImageName,foodPrice,foodOrderQuantity,userName)

    suspend fun loadCart(userName: String) : List<CartItem> = foodsDataSource.loadCart(userName)
}