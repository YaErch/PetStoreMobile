package com.example.petstoretest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.petstoretest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_add_pet -> {
                    navController.navigate(R.id.addPetFragment)
                    true
                }
                R.id.action_list_pets -> {
                    navController.navigate(R.id.listPetsFragment)
                    true
                }
                R.id.action_get_pet_by_id -> {
                    navController.navigate(R.id.getPetByIdFragment)
                    true
                }
                else -> false
            }
        }
    }
}
