package com.example.watchtvseries.ui.search
import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.data.model.TVShow
import com.example.watchtvseries.databinding.FragmentTvShowRowBinding
import com.example.watchtvseries.ui.tvshows.ItemClickListener
import com.example.watchtvseries.ui.tvshows.bindImage

class SearchViewHolder(private val binding: FragmentTvShowRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tvShow: TVShow, itemClickListener: ItemClickListener) {
        tvShow.image?.original?.let { binding.posterImageView.bindImage(it) }
        binding.titleTextView.text = tvShow.name
        binding.root.setOnClickListener {
            itemClickListener.onItemClick(tvShow)
        }
    }
}