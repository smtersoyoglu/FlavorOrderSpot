package com.sametersoyoglu.flavororderspot.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.data.entity.CartItem
import com.sametersoyoglu.flavororderspot.databinding.ItemCartBinding
import com.sametersoyoglu.flavororderspot.ui.viewmodel.CartViewModel

class CartAdapter (var mContext: Context, var cartFoodList : List<CartItem>, var viewModel:CartViewModel) : RecyclerView.Adapter<CartAdapter.CartItemHolder>(){

    private var quantity: Int = 1

    inner class CartItemHolder(var binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val binding: ItemCartBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_cart,parent,false)
        return CartItemHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {

        val cart = cartFoodList.get(position)
        val t = holder.binding

        t.foodNameTextView.text = cart.food_name
        t.foodPriceTextView.text = "₺${cart.food_price}"
        t.foodPriceTotalText.text = "${cart.getTotalPrice()} ₺"
        t.orderAmountText.text = cart.food_order_quantity.toString()

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cart.food_image_name}"
        Glide.with(mContext).load(url).into(t.imageViewFood)

        t.minusButton.setOnClickListener {
            if (cart.food_order_quantity > 0) {
                cart.food_order_quantity --
                t.orderAmountText.text = cart.food_order_quantity.toString()

                if (cart.food_order_quantity == 0) {
                    viewModel.deleteFoodFromCart(cart.cart_food_id,cart.username)
                }
                // Update the total price when decreasing quantity
                //t.foodPriceTotalText.text = "${cart.food_price * cart.food_order_quantity} ₺"
                //t.foodPriceTotalText.text = "${cart.getTotalPrice()} ₺"
            }
        }

        t.plusButton.setOnClickListener {

            /*
            val cartFood = cartFoodList.find { it.food_name == cart.food_name }
            if (cartFood != null) {
                // Eğer cartFoodList içinde bu yemek varsa
                viewModel.deleteFoodFromCart(cartFood.cart_food_id, cartFood.username)
                viewModel.addToCart(cartFood.food_name, cartFood.food_image_name, cartFood.food_price,cartFood.food_order_quantity + 1, "sametersoyoglu")
            } else {
                // Eğer cartFoodList içinde bu yemek yoksa, yenisini ekleyin
                viewModel.addToCart(cart.food_name, cart.food_image_name, cart.food_price, 1, "sametersoyoglu")
            }
            cart.food_order_quantity++
            t.orderAmountText.text = cart.food_order_quantity.toString()
             */

            cartFoodList.forEach {
                if(cart.food_name == it.food_name){
                    viewModel.deleteFoodFromCart(it.cart_food_id,it.username)
                    quantity += it.food_order_quantity
                    viewModel.addToCart(cart.food_name,cart.food_image_name,cart.food_price,quantity,"sametersoyoglu")
                }
            }
            cart.food_order_quantity++
            t.orderAmountText.text = cart.food_order_quantity.toString()


        }

        t.closeButton.setOnClickListener {
            val message = "${cart.food_name} sepetten çıkarılsın mı ?"
            Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                .setAction("EVET") {

                    viewModel.deleteFoodFromCart(cart.cart_food_id,cart.username)
                    viewModel.loadCart("sametersoyoglu")
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }

    fun CartItem.getTotalPrice(): Int {
        return food_price * food_order_quantity
    }

}