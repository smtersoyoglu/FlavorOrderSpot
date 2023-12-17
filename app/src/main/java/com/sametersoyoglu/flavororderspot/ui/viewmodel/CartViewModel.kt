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
    fun addToCart(food_name : String, food_image_name : String, food_price : Int,
                  food_order_quantity : Int, username : String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)
        }
        totalPrice()
    }
    fun loadCart(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cartFoodList.value = foodsRepository.loadCart(username)

            }catch (e:Exception){
                Log.e("sametersoyoglu","SepetViewModel başarısız $username")
            }
            totalPrice()
        }
    }
    fun deleteFoodFromCart(cart_food_id: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            foodsRepository.deleteFoodFromCart(cart_food_id,username)
            cartFoodList.value = foodsRepository.loadCart(username)

            totalPrice()
            loadCart(username)
        }
    }
    fun updateCart(cart: CartItem) {
        deleteFoodFromCart(cart.cart_food_id, cart.username)
        addToCart(cart.food_name, cart.food_image_name, cart.food_price, cart.food_order_quantity, "sametersoyoglu")
    }

    fun totalPrice(){
        val foodList = cartFoodList.value
        var total = 0

        foodList?.forEach { cart ->
            val foodPrice = cart.food_price
            val quantity = cart.food_order_quantity
            total += foodPrice * quantity
        }
        totalPrice.value = total
    }

}