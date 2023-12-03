package com.sametersoyoglu.flavororderspot.data.datasource

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
}