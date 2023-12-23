package com.sametersoyoglu.flavororderspot.di

import android.content.Context
import androidx.room.Room
import com.sametersoyoglu.flavororderspot.data.datasource.FoodsDataSource
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import com.sametersoyoglu.flavororderspot.retrofit.ApiUtils
import com.sametersoyoglu.flavororderspot.retrofit.FoodsDao
import com.sametersoyoglu.flavororderspot.room.FavFoodsDataBase
import com.sametersoyoglu.flavororderspot.room.FavoriteFoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodsDataSource(foodsDao: FoodsDao,favoriteFoodsDao: FavoriteFoodsDao): FoodsDataSource {
        return FoodsDataSource(foodsDao,favoriteFoodsDao)
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

    @Provides
    @Singleton
    fun provideFavoriteFoodsDao(@ApplicationContext context: Context) : FavoriteFoodsDao {
        val vt = Room.databaseBuilder(context,FavFoodsDataBase::class.java,"favoriteFoods.sqlite")
            .createFromAsset("favoriteFoods.sqlite").build()
        return vt.getFavoriteFoodsDao()
    }
}