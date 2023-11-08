package com.sametersoyoglu.flavororderspot.retrofit

import com.sametersoyoglu.flavororderspot.data.entity.FoodsResponse
import retrofit2.http.GET

interface FoodsDao {

    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFoods() : FoodsResponse


}