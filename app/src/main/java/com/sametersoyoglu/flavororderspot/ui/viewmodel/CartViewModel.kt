package com.sametersoyoglu.flavororderspot.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {

    var cartFoodList = MutableLiveData<List<CartItem>?>()
    var totalPrice = MutableLiveData<Int>()

    init {
        loadCart("sametersoyoglu")
    }

    fun addToCart(food_name : String,
                food_image_name : String,
                food_price : Int,
                food_order_quantity : Int,
                username : String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)
        }
    }

    fun loadCart(username:String) {

        CoroutineScope(Dispatchers.Main).launch {
            //cartFoodList.value = foodsRepository.loadCart(username)
            //totalPrice()
            try {
                cartFoodList.value = foodsRepository.loadCart(username)
            }catch (e:Exception){
                cartFoodList.value = null
                Log.e("hata mesajı",e.message.toString())
            }
            foodsRepository.loadCart(username).forEach{
                Log.d("sipariş",it.username)
            }
            totalPrice()
        }

    }


    fun deleteFoodFromCart(cart_food_id: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.deleteFoodFromCart(cart_food_id,username)
            cartFoodList.value = foodsRepository.loadCart(username)

            loadCart(username)
            totalPrice()
        }
    }

    private fun totalPrice(){
        val foodList = cartFoodList.value
        var total = 0

        foodList?.forEach { cartFood ->
            val foodPrice = cartFood.food_price
            val quantity = cartFood.food_order_quantity
            total += foodPrice * quantity
        }
        totalPrice.value = total
    }

}