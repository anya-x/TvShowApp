package com.example.watchtvseries.ui.tvshows

import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.data.model.TVShow
import com.example.watchtvseries.databinding.FragmentTvShowRowBinding

class TvShowViewHolder(private val binding: FragmentTvShowRowBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TVShow, itemClickListener: ItemClickListener) {
        binding.titleTextView.text = tvShow.name
        tvShow.image?.original?.let { binding.posterImageView.bindImage(it) }
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(tvShow)
        }
    }
}