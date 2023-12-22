package com.sametersoyoglu.flavororderspot.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "favorites")
data class FavoriteFoods(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "fav_food_id") @NotNull var fav_food_id:Int,
    @ColumnInfo(name = "food_id") @NotNull var food_id : Int,
    @ColumnInfo(name = "food_name") @NotNull var food_name: String,
    @ColumnInfo(name = "food_image_name") @NotNull var food_image_name: String,
    @ColumnInfo(name = "food_price") @NotNull var food_price: Int) : Serializable

