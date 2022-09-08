package com.example.dictionaryapp.ui.recyclerview.history

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.model.data.entity.DataModel

class HistoryAdapter : RecyclerView.Adapter<HistoryItemRecyclerViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryItemRecyclerViewHolder {
        return HistoryItemRecyclerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryItemRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(pos: Int): DataModel = data[pos]
}
