package com.example.watchtvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.viewModel.MyViewModel
import com.example.watchtvseries.viewModel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private lateinit var myViewModel: MyViewModel
    private val myAdapter by lazy { TvShowsAdapter() }
    private lateinit var myView: View
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        tvShowViewModel = ViewModelProvider(requireActivity()).get(TvShowViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        myView = inflater.inflate(R.layout.fragment_tv_shows, container, false)

        setUpRecyclerview()
        requestApiData()
        return myView
    }
    private fun setUpRecyclerview(){
        tvShowViewModel.recyclerView.adapter = myAdapter
        myView.recyclerview.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun requestApiData() {

        myViewModel.getTvShows(tvShowViewModel.applyQueries())
        myViewModel.APIResponse.observe(viewLifecycleOwner, { response ->
            when(response) {
                is APIResult.Success -> {
                    response.data?.let {myAdapter.setData(it)}
                }

                is APIResult.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        response.msg.toString(),
                        Toast.LENGTH_SHORT)
                }

                is APIResult.Load -> {
                }
            }
        })
    }




}