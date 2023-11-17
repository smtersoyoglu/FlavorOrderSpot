package com.sametersoyoglu.flavororderspot.retrofit

import com.sametersoyoglu.flavororderspot.data.entity.CRUDResponse
import com.sametersoyoglu.flavororderspot.data.entity.CartItemResponse
import com.sametersoyoglu.flavororderspot.data.entity.FoodsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {
    // suspend anahtar kelimesini kullanıyorsanız, Kotlin Coroutines kullanıyorsunuz demektir ve bu durumda Call tipi ve CallAdapter eklemenize gerek kalmaz
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods() : FoodsResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded // veri gönderirken türkçe desteği olsun diye kullanırız.
    suspend fun addToCart(@Field("yemek_adi") food_name : String, @Field("yemek_resim_adi") food_image_name : String,
                          @Field("yemek_fiyat") food_price : Int,@Field("yemek_siparis_adet") food_order_quantity : Int,
                          @Field("kullanici_adi") username : String) : CRUDResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded // veri gönderirken türkçe desteği olsun diye kullanırız.
    suspend fun loadCart(@Field("kullanici_adi") username: String) : CartItemResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded // veri gönderirken türkçe desteği olsun diye kullanırız.
    suspend fun deleteFoodFromCart(@Field("sepet_yemek_id") cart_food_id : Int, @Field("kullanici_adi") username: String) : CRUDResponse
}