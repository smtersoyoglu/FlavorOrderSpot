package com.sametersoyoglu.flavororderspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sametersoyoglu.flavororderspot.databinding.ActivityMainBinding
import com.sametersoyoglu.flavororderspot.ui.fragment.CartFragment
import com.sametersoyoglu.flavororderspot.ui.fragment.FavoriteFragment
import com.sametersoyoglu.flavororderspot.ui.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomnavigation , navHostFragment.navController)
    }
}