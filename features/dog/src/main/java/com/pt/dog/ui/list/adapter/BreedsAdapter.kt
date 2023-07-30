package com.pt.dog.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pt.dog.databinding.ItemBreedsBinding
import com.pt.dog.model.Breeds

class BreedsAdapter(
    private val goToBreedDetail: (breed: Breeds) -> Unit
) : RecyclerView.Adapter<BreedsAdapter.ItemViewHolder>() {

    private var listBreeds = mutableListOf<Breeds>()
    private var isLoadingMoreItems = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBreedsBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listBreeds[position]
        holder.bind(item)

        if (position == itemCount - 1 && itemCount < listBreeds.size) {
            isLoadingMoreItems = true
        }
    }

    override fun getItemCount(): Int {
        return listBreeds.size
    }

    fun addBreeds(newBreeds: List<Breeds>) {
        listBreeds.addAll(newBreeds)
        changeDataSet()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeDataSet() = notifyDataSetChanged()

    inner class ItemViewHolder(private val binding: ItemBreedsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Breeds) {
            binding.tvName.text = item.name

            Glide.with(binding.root.context)
                .load(item.image.url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivDog)

            binding.root.setOnClickListener {
                goToBreedDetail(item)
            }
        }
    }
}