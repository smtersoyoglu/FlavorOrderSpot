package com.sametersoyoglu.flavororderspot.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sametersoyoglu.flavororderspot.data.entity.FavoriteFoods

@Dao
interface FavoriteFoodsDao {

    @Query("SELECT * FROM favorites")
    suspend fun favoriteFoodsLoad() : List<FavoriteFoods>

    @Insert
    suspend fun addFavorite(favoriteFoods: FavoriteFoods)

    @Query("DELETE FROM favorites WHERE food_id = :food_id")
    suspend fun  deleteFavorite(food_id:Int)

    @Query("SELECT COUNT(*) FROM favorites WHERE food_id = :food_id")
    suspend fun isFavorite(food_id: Int): Int

}