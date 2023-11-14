package com.sametersoyoglu.flavororderspot.di

import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodsDataSource(foodsDao: FoodsDao): FoodsDataSource {
        return FoodsDataSource(foodsDao)
    }
}