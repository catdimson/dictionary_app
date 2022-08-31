package com.example.dictionaryapp.ui.recyclerview.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.databinding.ActivityHistoryRecyclerviewItemBinding
import com.example.dictionaryapp.model.data.entity.DataModel

class HistoryItemRecyclerViewHolder(
    private val binding: ActivityHistoryRecyclerviewItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): HistoryItemRecyclerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return HistoryItemRecyclerViewHolder(
                ActivityHistoryRecyclerviewItemBinding.inflate(
                    inflater
                )
            )
        }
    }

    fun bind(data: DataModel) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            binding.headerHistoryTextviewRecyclerItem.text = data.text
            binding.root.setOnClickListener {
                Toast.makeText(itemView.context, "on click: ${data.text}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}