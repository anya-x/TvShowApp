package com.example.watchtvseries.ui.tvshows



import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchtvseries.databinding.FragmentTvShowsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    lateinit var tvShowAdapter: TvShowAdapter

    private val tvShowViewModel: TvShowViewModel by viewModel()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //getting shows
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)


        //setting up tv show list
        tvShowAdapter = TvShowAdapter(ItemClickListener {
            val action = TvShowFragmentDirections.actionTvShowFragmentToDetailFragment(it)
            navController.navigate(action)
        })

        //setting up search navigation
        binding.searchButton.setOnClickListener {
            val action = TvShowFragmentDirections.actionTvShowFragmentToSearchFragment()
            navController.navigate(action)
        }

        if (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE || resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE) {

            binding.showsRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 5)
                adapter = tvShowAdapter
            }
        } else {
            binding.showsRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = tvShowAdapter
            }
        }
        lifecycleScope.launch {
            tvShowViewModel.flow.collectLatest { pagingData ->
                tvShowAdapter.submitData(pagingData)
            }
        }
        return binding.root
    }
}
