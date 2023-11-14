package com.sametersoyoglu.flavororderspot.data.repo

import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.data.entity.Foods

class FoodsRepository (var foodsDataSource: FoodsDataSource){


    suspend fun search(searchWord: String) : List<Foods> = foodsDataSource.search(searchWord)

}