package com.sametersoyoglu.flavororderspot.data.datasource

import android.util.Log
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.entity.FavoriteFoods
import com.sametersoyoglu.flavororderspot.data.entity.Foods
import com.sametersoyoglu.flavororderspot.retrofit.FoodsDao
import com.sametersoyoglu.flavororderspot.room.FavoriteFoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource (var foodsDao: FoodsDao, var favoriteFoodsDao: FavoriteFoodsDao) {

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

    suspend fun favoriteFoodsLoad() : List<FavoriteFoods> = withContext(Dispatchers.IO){
        return@withContext favoriteFoodsDao.favoriteFoodsLoad()
    }

    suspend fun addFavoriteFoods(food_id : Int, food_name:String,food_image_name:String,food_price:Int){
        val newFavoriteFood = FavoriteFoods(0,food_id,food_name,food_image_name,food_price)
        favoriteFoodsDao.addFavorite(newFavoriteFood)
    }

    suspend fun deleteFavorite(food_id: Int) {
        favoriteFoodsDao.deleteFavorite(food_id)
    }

    suspend fun isFavorite(food_id: Int): Int {
        return favoriteFoodsDao.isFavorite(food_id)
    }
}