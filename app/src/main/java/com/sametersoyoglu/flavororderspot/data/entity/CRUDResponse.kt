package com.sametersoyoglu.flavororderspot.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CRUDResponse (@SerializedName("success") var success: Int,
                    @SerializedName("message") var message: String) : Serializable
