package com.sametersoyoglu.flavororderspot.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.entity.Foods
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {

    var foodsList = MutableLiveData<List<Foods>?>()

    init {
        loadFoods()
    }
    fun loadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodsList.value = foodsRepository.loadFoods()
            /*
            try {
                val loadedFoods = foodsRepository.loadFoods()
                foodsList.value = loadedFoods
                loadedFoods.forEach {
                    Log.d("foods",it.food_name)
                }
            }catch (e: Exception){
                Log.e("foods","Hata oluştu: ${e.message}")
            }
             */
        }
    }
    fun search(query: String) {
        val result = foodsList.value?.filter { it.food_name.contains(query, ignoreCase = true) }
        foodsList.value = result
    }
}
