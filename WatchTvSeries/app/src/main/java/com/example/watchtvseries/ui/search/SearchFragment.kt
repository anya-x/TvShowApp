package com.example.watchtvseries.ui.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchtvseries.databinding.FragmentSearchBinding
import com.example.watchtvseries.ui.tvshows.ItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by viewModel()
    lateinit var searchAdapter: SearchAdapter

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        searchAdapter = SearchAdapter(ItemClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            navController.navigate(action)
        })

        if (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE || resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE) {

            binding.searchRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 5)
                adapter = searchAdapter
            }
        } else {
            binding.searchRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = searchAdapter
            }
        }

        binding.searchTextInput.setOnEditorActionListener { editText, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchViewModel.searchQuery(editText.text.toString())
            }
            true
        }
        searchViewModel.searchedShows.observe(viewLifecycleOwner, {
            searchAdapter.submitList(it)
        })
        return binding.root
    }
}
