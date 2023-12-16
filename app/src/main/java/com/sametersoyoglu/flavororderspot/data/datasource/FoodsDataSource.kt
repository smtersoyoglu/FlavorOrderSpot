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

    suspend fun addToCart(food_name : String, food_image_name : String,
                          food_price : Int, food_order_quantity : Int, username : String) {
        foodsDao.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)
    }

    suspend fun loadCart(username: String) : List<CartItem> =
        withContext(Dispatchers.IO) {
            try {
                return@withContext foodsDao.loadCart(username).cart_foods

            }catch (e:Exception){
                Log.e("hata mesajı",e.message.toString())
                return@withContext emptyList()
            }
        }

    suspend fun deleteFoodFromCart(cart_food_id: Int, username: String) {
        foodsDao.deleteFoodFromCart(cart_food_id,username)
    }

}