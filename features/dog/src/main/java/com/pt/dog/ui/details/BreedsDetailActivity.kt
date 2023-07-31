package com.pt.dog.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pt.dog.R
import com.pt.dog.databinding.ActivityBreedsDetailBinding
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

    private fun setupObservers() {
        viewModel.breedsLiveData.observe(this) { breed ->
            binding.tvBreedsName.text = breed.name
            binding.tvBredCategory.text = breed.bred_for
            binding.tvBreedOrigin.text = breed.origin
            binding.tvBreedTemperament.text = breed.temperament
        }

        viewModel.breedsImageLiveData.observe(this) { breed ->
            loadImage(breed.url.orEmpty())
        }

        viewModel.breedsErrorLiveData.observe(this) {
            binding.cardBreed.visibility = View.GONE
        }
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.icon_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivBreed)
    }

    private fun setupParams() {
        val id = intent?.extras?.getInt(PARAM_ID_BREED)
        viewModel.getBreedsById(id ?: 0)
    }

    companion object {
        private const val PARAM_ID_BREED = "breed"

        fun newInstance(context: Context, id: Int) =
            Intent(context, BreedsDetailActivity::class.java).apply {
                putExtra(PARAM_ID_BREED, id)
            }
    }
}