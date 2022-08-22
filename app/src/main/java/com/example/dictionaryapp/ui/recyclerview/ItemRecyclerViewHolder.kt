package com.example.dictionaryapp.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.databinding.ItemRecyclerViewBinding
import com.example.dictionaryapp.model.data.DataModel

class ItemRecyclerViewHolder(
    private val binding: ItemRecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ItemRecyclerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ItemRecyclerViewHolder(ItemRecyclerViewBinding.inflate(inflater))
        }
    }

    fun bind(itemData: DataModel, listener: (DataModel) -> Unit) {

        if (layoutPosition != RecyclerView.NO_POSITION) {
            binding.headerTextviewRecyclerItem.text = itemData.text
            binding.descriptionTextviewRecyclerItem.text =
                itemData.meanings?.get(0)?.translation?.text
            binding.root.setOnClickListener {
                listener.invoke(itemData)
            }
        }
    }
}