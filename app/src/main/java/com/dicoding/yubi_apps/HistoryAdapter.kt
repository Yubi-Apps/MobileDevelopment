package com.dicoding.yubi_apps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    private val history: List<History>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription1)
        val judulTextView: TextView = itemView.findViewById(R.id.tvJudul1)
        val imageView: ImageView = itemView.findViewById(R.id.ivIcon1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemhistory, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = history[position]
        holder.descriptionTextView.text = history.description
        holder.judulTextView.text = history.judul
        holder.imageView.setImageResource(history.imageResId)
    }

    override fun getItemCount(): Int = history.size
}