package com.example.watchtvseries.di
import com.example.watchtvseries.data.remote.TvShowAPIBuilder
import com.example.watchtvseries.data.remote.TvShowAPIService
import com.example.watchtvseries.repository.TvShowsRepository
import com.example.watchtvseries.ui.tvshows.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
val appModule = module {
    single<Retrofit> { TvShowAPIBuilder.getInstance() }
    single<TvShowAPIService> { TvShowAPIBuilder().tvShowAPIService }


    // single instance of HelloRepository
    factory<TvShowsRepository> { TvShowsRepository(get()) }

    // Simple Presenter Factory
    //factory { MySimplePresenter(get()) }

    viewModel { TvShowViewModel(get()) }
}