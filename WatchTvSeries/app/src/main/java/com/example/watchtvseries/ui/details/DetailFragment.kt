package com.example.watchtvseries.ui.details

import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.TypeConverters
import com.example.watchtvseries.data.model.TVShow
import com.example.watchtvseries.databinding.FragmentDetailsShowBinding
import com.example.watchtvseries.ui.genres.GenreAdapter
import com.example.watchtvseries.ui.tvshows.bindImage
import com.example.watchtvseries.util.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel


@TypeConverters(Converters::class)
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailsShowBinding
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var tvShow: TVShow
    private val detailViewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsShowBinding.inflate(inflater, container, false)
        tvShow = args.tvShow

        tvShow.image?.medium?.let { binding.posterDetailImageView.bindImage(it) }


        genreAdapter = GenreAdapter()
        genreAdapter.submitList(tvShow.genres)
        binding.genresRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = genreAdapter
        }

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch{
            val count = detailViewModel.checkIfFavorite(tvShow.id)
            withContext(Main){
                if (count > 0){
                    binding.favToggleBtn.isChecked = true
                    _isChecked = true
                }else{
                    binding.favToggleBtn.isChecked = false
                    _isChecked = false
                }
            }
        }

        binding.titleDetailTextView.text = tvShow.name
        binding.summaryDetailTextView.text = parseHtml(tvShow.summary)


        binding.favToggleBtn.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked){
                detailViewModel.addToFavorites(tvShow)
                Toast.makeText(context, "text", LENGTH_SHORT).show()
            } else{
                detailViewModel.removeFromFavorites(tvShow.id)
                Toast.makeText(context, "text2", LENGTH_SHORT).show()
            }
            binding.favToggleBtn.isChecked = _isChecked
        }

        return binding.root
    }

    private fun parseHtml(html: String): Spanned {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    }

}