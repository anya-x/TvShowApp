package com.example.watchtvseries.ui.details

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.watchtvseries.data.model.TVShow
import com.example.watchtvseries.databinding.FragmentDetailsShowBinding
import com.example.watchtvseries.ui.genres.GenreAdapter
import com.example.watchtvseries.ui.tvshows.bindImage

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailsShowBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var tvShow: TVShow
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsShowBinding.inflate(inflater, container, false)
        tvShow = args.tvShow

        tvShow.image?.medium?.let { binding.posterDetailImageView.bindImage(it) }
        binding.titleDetailTextView.text = tvShow.name
        binding.summaryDetailTextView.text = parseHtml(tvShow.summary)


        genreAdapter = GenreAdapter()
        genreAdapter.submitList(tvShow.genres)
        binding.genresRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = genreAdapter
        }

        return binding.root
    }

    private fun parseHtml(html: String): Spanned {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    }

}