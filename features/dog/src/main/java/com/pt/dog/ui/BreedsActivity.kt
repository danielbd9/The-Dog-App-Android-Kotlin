package com.pt.dog.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            isLoadingMoreItems = false
        }

        viewModel.breedsErrorLiveData.observe(this) {

        }
    }

    private fun setupView() {
        viewModel.fetchBreeds(page = 0)

        binding.btnToggle.setOnClickListener {
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
                    viewModel.loadMoreBreeds()
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = BreedsAdapter(::loadMoreItems)
        binding.rvBreeds.adapter = adapter
        binding.rvBreeds.layoutManager = listLayoutManager
    }

    private fun loadMoreItems() {
        viewModel.loadMoreBreeds()
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
}