package com.example.watchtvseries.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.watchtvseries.model.TvShowJsonDataItem

// DiffUtil used to update only new tv show items instead of the whole list
class TvShowDiffUtil (
    private val oldList: List<TvShowJsonDataItem>,
    private val newList: List<TvShowJsonDataItem>
    ): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
// function called by DiffUtil to decide if two objects represent the same item
// in the newList and oldList
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }
// function checks whether two objects have the same content,
// only be called if fun areItemsTheSame returns true
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}