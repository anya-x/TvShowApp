package com.example.watchtvseries.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class TvShowViewModel(application: Application) : AndroidViewModel(application){
    val QUERY_STR : String = "lala"
    fun applyQueries(): HashMap<String, String> {

        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_STR] = "anything"

        return queries
    }
}
