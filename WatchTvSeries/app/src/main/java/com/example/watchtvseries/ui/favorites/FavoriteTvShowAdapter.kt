package com.example.watchtvseries.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.data.model.FavoriteTvShow
import com.example.watchtvseries.databinding.FragmentTvShowRowBinding

import com.example.watchtvseries.ui.tvshows.bindImage
import kotlinx.android.synthetic.main.fragment_details_show.view.*

class FavoriteTvShowAdapter: RecyclerView.Adapter<FavoriteTvShowAdapter.FavoriteTvShowViewHolder>() {
    private lateinit var list : List<FavoriteTvShow>
    private lateinit var favoriteTvShowViewModel: FavoriteTvShowViewModel
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTvShowList(list: List<FavoriteTvShow>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class FavoriteTvShowViewHolder(private val binding: FragmentTvShowRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteTvShow: FavoriteTvShow) {
            with(binding) {
                favoriteTvShow.image?.original?.let { binding.posterImageView.bindImage(it) }
                binding.titleTextView.text = favoriteTvShow.name
                binding.root.setOnClickListener { onItemClickCallback?.onItemClick(favoriteTvShow) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        val binding = FragmentTvShowRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvShowViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        holder.bind(list[position])
    }


    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: FavoriteTvShow)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }


}
class FavoriteItemClickListener(val click: (favTvShow: FavoriteTvShow) -> Unit) {
    fun onItemClick(favTvShow: FavoriteTvShow) {
        click(favTvShow)
    }
}
private class ShowsDiffCallback : DiffUtil.ItemCallback<FavoriteTvShow>() {
    override fun areItemsTheSame(oldItem: FavoriteTvShow, newItem: FavoriteTvShow): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FavoriteTvShow, newItem: FavoriteTvShow): Boolean {
        return oldItem == newItem
    }

}