package com.sametersoyoglu.flavororderspot.di

import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import com.sametersoyoglu.flavororderspot.retrofit.ApiUtils
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

    @Provides
    @Singleton
    fun provideFoodsRepository(foodsDataSource: FoodsDataSource): FoodsRepository {
        return FoodsRepository(foodsDataSource)
        // TaskRepository den nesne oluşturacaksak TaskDataSource ta tanımlı olduğu için TaskDataSource' u da tanımlamamız lazım burda.
    }

    @Provides
    @Singleton
    fun provideFoodsDao() : FoodsDao {
        return ApiUtils.getFoodsDao()
    }

}