package com.example.dictionaryapp.ui.recyclerview.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.model.data.entity.DataModel

class RecyclerAdapter(
    private var itemClickCallback: OnListItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<ItemRecyclerViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickCallback)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(pos: Int): DataModel = data[pos]

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}