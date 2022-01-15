package com.example.watchtvseries.ui.tvshows



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchtvseries.databinding.FragmentTvShowsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    lateinit var tvShowAdapter: TvShowAdapter

    private val tvShowViewModel: TvShowViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)

        tvShowAdapter = TvShowAdapter(ItemClickListener {
        })

        binding.showsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = tvShowAdapter
        }

        lifecycleScope.launch {
            tvShowViewModel.flow.collectLatest { pagingData ->
                tvShowAdapter.submitData(pagingData)
            }
        }

        return binding.root
    }
}
