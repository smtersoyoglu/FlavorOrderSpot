package com.sametersoyoglu.flavororderspot.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sametersoyoglu.flavororderspot.R
import com.sametersoyoglu.flavororderspot.databinding.FragmentFoodDetailsBinding
import com.sametersoyoglu.flavororderspot.ui.viewmodel.FoodDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint

class FoodDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var viewModel: FoodDetailsViewModel
    private var booleanFavState : Boolean = false
    private var count = 1
    private var totalPrice = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModeli bağlama - onCreate içersinde olur bu işlem
        val tempViewModel : FoodDetailsViewModel by viewModels() // gecici bir viewmodele atayıp ordan bizim viewmodelimize bağlarız.
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodDetailsFragment = this
        binding.foodCount = count

        val bundle: FoodDetailsFragmentArgs by navArgs()
        val receivedFood = bundle.food
        binding.foodObject = receivedFood

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${receivedFood.food_image_name}"
        Glide.with(this).load(url).into(binding.foodImage)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.favState.value = viewModel.isFavorite(receivedFood.food_id).toString()
        }

        viewModel.favState.observe(viewLifecycleOwner){
            if (viewModel.favState.value!!.toInt() > 0){
                binding.favImageView.setImageResource(R.drawable.heart_red)

            }else{
                binding.favImageView.setImageResource(R.drawable.heart_gray)

            }
        }

        binding.exitImageView.setOnClickListener {
            val action = FoodDetailsFragmentDirections.foodDetailsFragmentTohomeFragment()
            findNavController().navigate(action)
        }

}
    fun onAddToCartButtonClicked(food_name : String, food_image_name : String, food_price : Int,
                  food_order_quantity : Int, username : String) {
        viewModel.addToCart(food_name,food_image_name,food_price,food_order_quantity,username)
        val action = FoodDetailsFragmentDirections.foodDetailsFragmentTocartFragment()
        findNavController().navigate(action)
    }
    fun buttonMinus() {
        if (count > 1) {
            count--
            binding.foodCount = count
            totalPrice()
            //binding.foodPrice.text = "${count * binding.foodObject!!.food_price}  ₺"
        }
    }
    fun buttonPlus() {
        count++
        binding.foodCount = count
        totalPrice()
        //binding.foodPrice.text = "${count * binding.foodObject!!.food_price}  ₺"
    }

    fun totalPrice() {
        totalPrice = count * binding.foodObject!!.food_price
        binding.totalPriceText.text = "${totalPrice} ₺"
    }


    fun addFavoriteFoods(food_id : Int, food_name:String,food_image_name:String,food_price:Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val isFavorite = viewModel.isFavorite(food_id)
            if (isFavorite == 1){
                // Yemek zaten favorilerde, bu yüzden tekrar eklemeyin.
                Toast.makeText(context, "Bu yemek zaten favorilerinizde.", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.addFavoriteFoods(food_id,food_name,food_image_name,food_price)
                binding.favImageView.setImageResource(R.drawable.heart_red)
                booleanFavState = true
            }
        }
    }


}