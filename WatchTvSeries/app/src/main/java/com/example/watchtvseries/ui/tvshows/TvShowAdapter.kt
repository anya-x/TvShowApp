package com.example.watchtvseries.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchtvseries.data.remote.TVShow
import com.example.watchtvseries.databinding.FragmentTvShowRowBinding

class TvShowAdapter (private val itemClickListener: ItemClickListener) :
    PagingDataAdapter<TVShow, TvShowAdapter.TvShowViewHolder>(ShowsDiffCallback()) {
    class TvShowViewHolder(private val binding: FragmentTvShowRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShow, itemClickListener: ItemClickListener) {
            binding.titleTextView.text = tvShow.name
            binding.posterImageView.bindImage(tvShow)
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(tvShow)
            }
        }

    }
    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let {
            holder.bind(it, itemClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val singleShow = FragmentTvShowRowBinding.inflate(layoutInflater, parent, false)
        return TvShowViewHolder(singleShow)
    }

    }

class ItemClickListener(val click: (tvShow: TVShow) -> Unit) {
    fun onItemClick(tvShow: TVShow) {
        click(tvShow)
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

