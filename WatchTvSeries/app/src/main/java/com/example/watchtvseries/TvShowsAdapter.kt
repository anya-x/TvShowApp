package com.example.watchtvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.databinding.ShowsListRowBinding
import com.example.watchtvseries.model.TvShowJsonData
import com.example.watchtvseries.model.TvShowJsonDataItem
import com.example.watchtvseries.utils.TvShowDiffUtil


class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.MyViewHolder>() {

    private var tvShows = emptyList<TvShowJsonDataItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow = tvShows[position]
        holder.bind(currentTvShow)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
// we get results from TvShowJsonData, then store this result to the emptylist of tvShow variable
// the emptyList then will be our oldList. DiffUtil will calculate the difference and update
// the view which are not the same
    fun setData(newData:TvShowJsonData) {
        val tvShowDiffUtil = TvShowDiffUtil(tvShows, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(tvShowDiffUtil)
        tvShows = newData.results
        diffUtilResult.dispatchUpdatesTo(this)


    }

    class MyViewHolder(private val binding:ShowsListRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(result: TvShowJsonDataItem){
            binding.apiResult = result
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup):MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ShowsListRowBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)

            }

        }

    }
}