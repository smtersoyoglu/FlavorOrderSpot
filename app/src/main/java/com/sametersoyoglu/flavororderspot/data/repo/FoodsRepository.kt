package com.sametersoyoglu.flavororderspot.data.repo

import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.entity.FavoriteFoods
import com.sametersoyoglu.flavororderspot.data.entity.Foods

class FoodsRepository (var foodsDataSource: FoodsDataSource){

    suspend fun loadFoods() : List<Foods> = foodsDataSource.loadFoods()

    suspend fun addToCart(food_name : String, food_image_name : String, food_price : Int,
                          food_order_quantity : Int,
                          username : String) = foodsDataSource.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)

    suspend fun loadCart(username: String) : List<CartItem> = foodsDataSource.loadCart(username)
    suspend fun deleteFoodFromCart(cart_food_id: Int, username: String) = foodsDataSource.deleteFoodFromCart(cart_food_id,username)

    suspend fun favoriteFoodsLoad() : List<FavoriteFoods> = foodsDataSource.favoriteFoodsLoad()

    suspend fun addFavoriteFoods(food_id : Int, food_name:String,food_image_name:String,food_price:Int) = foodsDataSource.addFavoriteFoods(food_id,food_name,food_image_name,food_price)

    suspend fun deleteFavorite(food_id: Int) = foodsDataSource.deleteFavorite(food_id)

    suspend fun isFavorite(food_id: Int): Int = foodsDataSource.isFavorite(food_id)
}