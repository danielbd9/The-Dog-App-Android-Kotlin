package com.pt.dog.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pt.dog.databinding.ActivityBreedsDetailBinding
import com.pt.dog.model.Breeds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupParams()
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadImage(url: String) {
        Glide.with(binding.root.context)
            .load(url)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivBreed)
    }

    private fun setupParams() {
        intent?.getParcelableExtra<Breeds>(PARAM_BREED)?.let { breed ->
            binding.tvBreedsName.text = breed.name
            binding.tvBredCategory.text = breed.bred_for
            binding.tvBreedOrigin.text = breed.origin
            binding.tvBreedTemperament.text = breed.temperament
            loadImage(breed.image?.url.orEmpty())
        }
    }

    companion object {
        private const val PARAM_BREED = "breed"

        fun newInstance(context: Context, breed: Breeds) =
            Intent(context, BreedsDetailActivity::class.java).apply {
                putExtra(PARAM_BREED, breed)
            }
    }
}