package com.example.watchtvseries.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchtvseries.data.remote.TVShow
import com.example.watchtvseries.databinding.FragmentTvShowRowBinding

class TvShowAdapter :
    ListAdapter<TVShow, TvShowAdapter.TvShowViewHolder>(ShowsDiffCallback()) {

        class TvShowViewHolder(private val binding: FragmentTvShowRowBinding): RecyclerView.ViewHolder(binding.root) {

            fun bind(tvShow: TVShow) {
                binding.posterImageView.bindImage(tvShow)
                binding.titleTextView.text = tvShow.name
                }

            }
    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val singleShow = FragmentTvShowRowBinding.inflate(layoutInflater, parent, false)
        return TvShowViewHolder(singleShow)
    }

    }

fun ImageView.bindImage(show: TVShow) {
    Glide.with(this.context)
        .load(show.image.original)
        .into(this)
}

    private class ShowsDiffCallback : DiffUtil.ItemCallback<TVShow>() {

        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem == newItem
        }
}
