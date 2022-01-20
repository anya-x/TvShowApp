package com.example.watchtvseries.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.watchtvseries.data.model.SearchedShow
import com.example.watchtvseries.databinding.FragmentTvShowRowBinding
import com.example.watchtvseries.ui.tvshows.ItemClickListener


class SearchAdapter(private val itemClickListener: ItemClickListener) : ListAdapter<SearchedShow, SearchViewHolder>(SearchDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val singleShow = FragmentTvShowRowBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(singleShow)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchedShow = getItem(position)
        searchedShow?.let {
            holder.bind(it.show, itemClickListener)
        }
    }


}

private class SearchDiffCallback : DiffUtil.ItemCallback<SearchedShow>() {
    override fun areItemsTheSame(oldItem: SearchedShow, newItem: SearchedShow): Boolean {
        return oldItem.show.id == newItem.show.id
    }

    override fun areContentsTheSame(oldItem: SearchedShow, newItem: SearchedShow): Boolean {
        return oldItem == newItem
    }

}