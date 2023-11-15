package com.sametersoyoglu.flavororderspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {
}