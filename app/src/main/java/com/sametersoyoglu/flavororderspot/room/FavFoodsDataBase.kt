package com.sametersoyoglu.flavororderspot.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sametersoyoglu.flavororderspot.data.entity.FavoriteFoods

@Database(entities = [FavoriteFoods::class], version = 1)
abstract class FavFoodsDataBase : RoomDatabase() {

    abstract fun getFavoriteFoodsDao() : FavoriteFoodsDao


}