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
            adapter.addList(dogsList)
            onToggleLayoutClick()
        }

        viewModel.breedsErrorLiveData.observe(this) {

        }
    }

    private fun setupView() {
        viewModel.fetchBreeds(page = 0)

        binding.btnToggle.setOnClickListener {
            onToggleLayoutClick()
        }
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
        adapter.changeOptionsGrid()
    }
}