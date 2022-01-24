package com.example.watchtvseries.ui.favorites


import android.os.Bundle

import android.view.View

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchtvseries.R
import com.example.watchtvseries.data.model.FavoriteTvShow
import com.example.watchtvseries.data.model.TVShow
import com.example.watchtvseries.databinding.FragmentFavTvShowsBinding
import kotlinx.android.synthetic.main.fragment_fav_tv_shows.*
import org.koin.android.viewmodel.ext.android.viewModel




class FavoriteTvShowFragment : Fragment(R.layout.fragment_fav_tv_shows) {
    private val favoriteTvShowViewModel: FavoriteTvShowViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favTvShowAdapter = FavoriteTvShowAdapter()
        val binding = FragmentFavTvShowsBinding.bind(view)

        fav_tvShows_recyclerView.layoutManager = GridLayoutManager(activity,2)

        favoriteTvShowViewModel.favTvShows.observe(viewLifecycleOwner){
            favTvShowAdapter.setTvShowList(it)
            binding.apply {
                fav_tvShows_recyclerView.setHasFixedSize(true)
                fav_tvShows_recyclerView.adapter = favTvShowAdapter
            }
        }

        favTvShowAdapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback{
            override fun onItemClick(favoriteTvShow: FavoriteTvShow) {
                val tvShow = TVShow(
                    favoriteTvShow.id_tvshow,
                    favoriteTvShow.name,
                    favoriteTvShow.image,
                    favoriteTvShow.genres,
                    favoriteTvShow.summary
                )
                val action = FavoriteTvShowFragmentDirections.actionNavFavoritesToDetailFragment(tvShow)
                findNavController().navigate(action)
            }

        })
    }


}


