package com.sametersoyoglu.flavororderspot.data.repo

import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.entity.Foods

class FoodsRepository (var foodsDataSource: FoodsDataSource){

    suspend fun loadFoods() : List<Foods> = foodsDataSource.loadFoods()

    suspend fun addToCart(food_name : String, food_image_name : String, food_price : Int,
                          food_order_quantity : Int,
                          username : String) = foodsDataSource.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)

    suspend fun loadCart(username: String) : List<CartItem> = foodsDataSource.loadCart(username)
    suspend fun deleteFoodFromCart(cart_food_id: Int, username: String) = foodsDataSource.deleteFoodFromCart(cart_food_id,username)
}