package com.sametersoyoglu.flavororderspot.data.entity

import com.google.gson.annotations.SerializedName

class CartItemResponse (@SerializedName("sepet_yemekler") var cart_foods: List<CartItem>,
                        @SerializedName("success") var success: Int) {
}
