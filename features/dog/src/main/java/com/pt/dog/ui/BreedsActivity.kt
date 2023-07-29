package com.pt.dog.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pt.dog.databinding.ActivityDogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogBinding
    private val viewModel: BreedsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        viewModel.fetchBreeds()
    }
}