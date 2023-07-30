package com.pt.dog.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pt.dog.databinding.ActivityBreedsDetailBinding
import com.pt.dog.model.Breeds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedsDetailBinding
    private val viewModel: BreedsDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupObservers()
        setupParams()
    }

    private fun setupParams() {

    }

    private fun setupObservers() {

    }

    private fun setupView() {

    }

    companion object {
        private const val PARAM_BREED = "breed"

        fun newInstance(context: Context, breed: Breeds) =
            Intent(context, BreedsDetailActivity::class.java).apply {
                putExtra(PARAM_BREED, breed)
            }
    }
}