package com.example.watchtvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.viewModel.MyViewModel
import com.example.watchtvseries.viewModel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private lateinit var myViewModel: MyViewModel
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var myView: View
    private lateinit var mRecyclerView: RecyclerView
    private val myAdapter by lazy { TvShowsAdapter() }


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
        val mRecyclerView= myView.findViewById<RecyclerView>(R.id.recyclerView)
        setUpRecyclerView()
        requestApiData()
        return myView
    }

    private fun requestApiData() {

        myViewModel.getTvShows(tvShowViewModel.applyQueries())
        myViewModel.APIResponse.observe(viewLifecycleOwner, {  response->
            when(response) {
                is APIResult.Success -> {
                    response.data?.let {myAdapter.setData(it)}
                }

                is APIResult.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        response.msg.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        mRecyclerView.adapter = myAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}