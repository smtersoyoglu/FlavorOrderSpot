package com.sametersoyoglu.flavororderspot.data.entity

import com.google.gson.annotations.SerializedName

class CRUDResponse (@SerializedName("success") var success: Int,
                    @SerializedName("message") var message: String)
