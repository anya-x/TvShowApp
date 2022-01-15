package com.example.watchtvseries.ui.tvshows
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.watchtvseries.data.remote.TVShow
import com.example.watchtvseries.repository.TvShowsRepository

class TvShowPagingSource(val tvShowsRepository: TvShowsRepository) : PagingSource<Int, TVShow>() {

    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        return try {
            val next = params.key ?: 0
            val response = tvShowsRepository.getShows(next)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    }

