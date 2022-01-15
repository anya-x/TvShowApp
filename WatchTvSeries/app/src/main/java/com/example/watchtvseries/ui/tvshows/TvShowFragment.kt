package com.example.watchtvseries.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchtvseries.databinding.FragmentTvShowsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    lateinit var tvShowAdapter: TvShowAdapter

    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        tvShowViewModel.getShows(0)

        tvShowViewModel.shows.observe(viewLifecycleOwner, {
            tvShowAdapter.submitList(it)
        })

        tvShowAdapter= TvShowAdapter()
        binding.showsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = tvShowAdapter
        }

        return binding.root
    }

}