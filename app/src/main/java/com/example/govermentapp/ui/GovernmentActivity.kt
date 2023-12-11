package com.example.govermentapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.govermentapp.R
import com.example.govermentapp.databinding.ActivityGovernmentBinding

class GovernmentActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityGovernmentBinding
    private val navController by lazy { findNavController(R.id.government_nav_controller) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGovernmentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initNavigation()
    }

    private fun initNavigation() {
        navController.addOnDestinationChangedListener { _, _, _ ->
        }
    }
}