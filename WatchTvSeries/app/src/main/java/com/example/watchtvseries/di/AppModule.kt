package com.example.watchtvseries.di
import com.example.watchtvseries.api.TvShowAPIService
import com.example.watchtvseries.data.local.*
import com.example.watchtvseries.data.remote.TvShowAPIBuilder
import com.example.watchtvseries.data.remote.TvShowsRepository
import com.example.watchtvseries.ui.details.DetailViewModel
import com.example.watchtvseries.ui.favorites.FavoriteTvShowViewModel
import com.example.watchtvseries.ui.search.SearchViewModel
import com.example.watchtvseries.ui.tvshows.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
val appModule = module {
    single<Retrofit> { TvShowAPIBuilder.getInstance() }
    single<TvShowAPIService> { TvShowAPIBuilder().tvShowAPIService }



    factory<TvShowsRepository> { TvShowsRepository(get()) }



    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}

val databaseModule = module {
    single<FavoriteTVShowDao> {DataBaseBuilder.getInstance(get())}
    //single {get<FavoriteTvShowDatabase>().getFavoriteTvShowDao()}

    factory<FavoriteTvShowsRepository> { FavoriteTvShowsRepository(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }

}