package com.example.watchtvseries.ui.genres

import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.databinding.GenreItemBinding

class GenreViewHolder(private val binding: GenreItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: String) {
        binding.genreItemChip.text = genre
    }
}