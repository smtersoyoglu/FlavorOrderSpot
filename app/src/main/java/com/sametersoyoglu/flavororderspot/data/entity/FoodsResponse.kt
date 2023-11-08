package com.sametersoyoglu.flavororderspot.data.entity

import com.google.gson.annotations.SerializedName

class FoodsResponse (@SerializedName("yemekler") var foods:List<Foods>,
                     @SerializedName("success") var success:Int)

