package com.example.watchtvseries.viewModel;

import android.app.Application;

import javax.inject.Inject;

class TvShowViewModel(application:Application) : AndroidViewModel(application){

    fun applyQueries(): HashMap<String, String> {

        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_STR] = "anything"

        return queries
    }
}
