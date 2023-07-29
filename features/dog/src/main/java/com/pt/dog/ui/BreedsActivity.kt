package com.pt.dog.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pt.dog.R
import com.pt.dog.databinding.ActivityBreedsBinding
import com.pt.dog.ui.adapter.BreedsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedsBinding
    private val viewModel: BreedsViewModel by viewModels()

    private val listLayoutManager = LinearLayoutManager(this)
    private val gridLayoutManager = GridLayoutManager(this, 2)

    private lateinit var adapter: BreedsAdapter
    private var layoutManager: RecyclerView.LayoutManager = listLayoutManager

    private var isLoadingMoreItems = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.breedsLiveData.observe(this) { dogsList ->
            adapter.addBreeds(dogsList)
            binding.llLoading.visibility = View.GONE
            isLoadingMoreItems = false
        }

        viewModel.breedsErrorLiveData.observe(this) {

        }
    }

    private fun setupView() {
        animation(R.drawable.animation_list, binding.ivLoading)

        viewModel.fetchBreeds(page = 0)

        binding.btnToggle.setOnClickListener {
            onToggleLayoutClick()
        }

        binding.tvGrid.setOnClickListener {
            onToggleLayoutClick()
        }

        binding.rvBreeds.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = when (layoutManager) {
                    is LinearLayoutManager -> (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    is GridLayoutManager -> (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    else -> RecyclerView.NO_POSITION
                }

                val totalItemCount = layoutManager.itemCount
                if (!isLoadingMoreItems && lastVisibleItemPosition == totalItemCount - 1) {
                    isLoadingMoreItems = true
                    binding.llLoading.visibility = View.VISIBLE
                    viewModel.loadMoreBreeds()
                }
            }
        })
    }

    private fun animation(drawable: Int, imageView: ImageView){
        imageView.setBackgroundResource(drawable)
        val animation = imageView.background as AnimationDrawable
        animation.setEnterFadeDuration(PARAM_FADE)
        animation.setExitFadeDuration(PARAM_FADE)
        animation.start()
    }

    private fun setupAdapter() {
        adapter = BreedsAdapter()
        binding.rvBreeds.adapter = adapter
        binding.rvBreeds.layoutManager = listLayoutManager
    }

    private fun onToggleLayoutClick() {
        layoutManager = if (layoutManager == listLayoutManager) {
            gridLayoutManager
        } else {
            listLayoutManager
        }
        binding.rvBreeds.layoutManager = layoutManager
        adapter.changeDataSet()
    }

    companion object {
        private const val PARAM_FADE = 1500
    }
}