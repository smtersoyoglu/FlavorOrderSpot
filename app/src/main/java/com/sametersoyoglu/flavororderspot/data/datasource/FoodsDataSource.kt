package com.sametersoyoglu.flavororderspot.data.datasource

import android.util.Log
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.entity.Foods
import com.sametersoyoglu.flavororderspot.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource (var foodsDao: FoodsDao) {

    suspend fun loadFoods() : List<Foods> =
        withContext(Dispatchers.IO){
        return@withContext foodsDao.getAllFoods().foods
        }

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodOrderQuantity: Int, userName: String) {
        foodsDao.addToCart(foodName,foodImageName,foodPrice,foodOrderQuantity,userName)
    }

    suspend fun loadCart(username: String) : List<CartItem> =
        withContext(Dispatchers.IO) {
            return@withContext foodsDao.loadCart(username).cart_foods
        }

    suspend fun deleteFoodFromCart(cart_food_id: Int, username: String) {
        foodsDao.deleteFoodFromCart(cart_food_id,username)
    }

}