package com.example.dictionaryapp.ui.recyclerview.history

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.data.entity.DataModel

class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: DataModel) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.findViewById<TextView>(R.id.header_history_textview_recycler_item).text =
                data.text
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "on click: ${data.text}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}