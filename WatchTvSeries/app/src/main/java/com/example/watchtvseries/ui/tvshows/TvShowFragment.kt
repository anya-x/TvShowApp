package com.example.watchtvseries.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.watchtvseries.databinding.FragmentTvShowsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    lateinit var binding: FragmentTvShowsBinding

    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        tvShowViewModel.getShows(0)
        tvShowViewModel.shows.observe(viewLifecycleOwner, Observer {

        })
        return binding.root
    }

}