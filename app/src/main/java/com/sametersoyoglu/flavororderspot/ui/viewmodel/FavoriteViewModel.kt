package com.sametersoyoglu.flavororderspot.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.entity.FavoriteFoods
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {

    var favoriteFoodList = MutableLiveData<List<FavoriteFoods>>()

    init {
        favoriteFoodsLoad()
    }
    fun favoriteFoodsLoad() {
        CoroutineScope(Dispatchers.Main).launch {
            favoriteFoodList.value = foodsRepository.favoriteFoodsLoad()
        }
    }

    fun deleteFavorite(food_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.deleteFavorite(food_id)
            favoriteFoodsLoad()
        }
    }
}