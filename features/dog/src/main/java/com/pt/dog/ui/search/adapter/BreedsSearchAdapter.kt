package com.pt.dog.ui.search.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pt.dog.databinding.ItemBreedsSearchBinding
import com.pt.dog.model.Breeds

class BreedsSearchAdapter(
    private val goToBreedDetail: (breed: Breeds) -> Unit
) : RecyclerView.Adapter<BreedsSearchAdapter.ItemViewHolder>() {

    private var listBreeds = mutableListOf<Breeds>()
    private var ascendingOrder = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBreedsSearchBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listBreeds[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listBreeds.size
    }

    fun addBreeds(newBreeds: List<Breeds>) {
        listBreeds.clear()
        listBreeds.addAll(newBreeds)
        sortBreeds()
        changeDataSet()
    }

    fun toggleSortingOrder() {
        ascendingOrder = !ascendingOrder
        sortBreeds()
        changeDataSet()
    }

    private fun sortBreeds() {
        listBreeds.sortWith(compareBy { it.name })

        if (!ascendingOrder)
            listBreeds.reverse()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeDataSet() = notifyDataSetChanged()

    inner class ItemViewHolder(private val binding: ItemBreedsSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Breeds) {
            binding.tvName.text = item.name
            binding.tvBredGroup.text = item.breed_group
            binding.tvOrigin.text = item.origin
            binding.tvTemperament.text = item.temperament

            clickCard(item)
        }

        private fun clickCard(item: Breeds) {
            binding.card.setOnClickListener {
                goToBreedDetail(item)
            }
        }
    }
}