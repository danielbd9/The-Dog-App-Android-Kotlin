package com.pt.dog.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pt.dog.R
import com.pt.dog.databinding.ActivityBreedsSearchBinding
import com.pt.dog.ui.search.adapter.BreedsSearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedsSearchBinding
    private val viewModel: BreedsSearchViewModel by viewModels()

    private val listLayoutManager = LinearLayoutManager(this)
    private val gridLayoutManager = GridLayoutManager(this, PARAM_GRID_COUNT)

    private lateinit var adapter: BreedsSearchAdapter
    private var layoutManager: RecyclerView.LayoutManager = listLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedsSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAdapter()
        setupObservers()
        setupParams()
    }

    private fun setupObservers() {
        viewModel.breedsLiveData.observe(this) { dogsList ->
            adapter.addBreeds(dogsList)
            binding.rvBreeds.visibility = View.VISIBLE
            binding.llLoading.visibility = View.GONE
            binding.ivSearchError.visibility = View.GONE
            binding.tvSearchError.visibility = View.GONE
        }

        viewModel.breedsErrorLiveData.observe(this) {

        }

        viewModel.breedsErrorSearchLiveData.observe(this) {
            binding.rvBreeds.visibility = View.GONE
            binding.ivSearchError.visibility = View.VISIBLE
            binding.tvSearchError.visibility = View.VISIBLE
        }
    }

    private fun setupView() {
        binding.ivGrid.setOnClickListener {
            changeLayoutView()
        }

        binding.iSearch.etSearch.setOnEditorActionListener(
            TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val term = binding.iSearch.etSearch.text.toString()
                    viewModel.getSearchBreeds(term)
                    return@OnEditorActionListener true
                }
                false
            }
        )
    }

    private fun setupAdapter() {
        adapter = BreedsSearchAdapter()
        binding.rvBreeds.adapter = adapter
        binding.rvBreeds.layoutManager = listLayoutManager
    }

    private fun setupParams() {
        val term = intent?.extras?.getString(PARAM_TERM).orEmpty()
        binding.iSearch.etSearch.setText(term)
        binding.llLoading.visibility = View.VISIBLE
        viewModel.getSearchBreeds(term)
    }

    private fun changeLayoutView() {
        if (layoutManager == listLayoutManager) {
            layoutManager = gridLayoutManager
            binding.ivGrid.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_list))
        } else {
            layoutManager = listLayoutManager
            binding.ivGrid.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_grid))
        }
        binding.rvBreeds.layoutManager = layoutManager
        adapter.changeDataSet()
    }

    companion object {
        private const val PARAM_TERM = "term"
        private const val PARAM_GRID_COUNT = 2

        fun newInstance(context: Context, term: String) =
            Intent(context, BreedsSearchActivity::class.java).apply {
                putExtra(PARAM_TERM, term)
            }
    }
}