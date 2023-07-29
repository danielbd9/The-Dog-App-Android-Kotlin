package com.pt.dog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pt.dog.databinding.ItemBreedsBinding
import com.pt.dog.model.Breeds

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.ItemViewHolder>() {

    var listBreeds = mutableListOf<Breeds>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBreedsBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listBreeds[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listBreeds.size
    }

    fun addList(breeds: List<Breeds>){
        this.listBreeds.addAll(breeds)
    }

    fun changeOptionsGrid(){
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemBreedsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Breeds) {
            binding.tvName.text = item.name
        }
    }
}