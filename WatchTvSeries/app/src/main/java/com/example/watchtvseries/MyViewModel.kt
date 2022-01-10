package com.example.watchtvseries

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.watchtvseries.data.Repository
import com.example.watchtvseries.model.TvShowJsonData
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MyViewModel @Inject constructor(
    private val repository: Repository,
    application: Application): AndroidViewModel(application) {

    private fun canConnectToInternet(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            else -> false
        }
    }

    val APIResponse: MutableLiveData<APIResult<TvShowJsonData>> = MutableLiveData()

    fun getTvShows(queries: Map<String, String>) = viewModelScope.launch {
        getTvShowsSafeMode(queries)
    }

    private suspend fun getTvShowsSafeMode(queries: Map<String, String>) {
        APIResponse.value = APIResult.Load()
        if (canConnectToInternet()) {
            try {
                val response = repository.remoteDatas.getTvShow(queries)
                APIResponse.value = APIResponseHandler(response)
            } catch (e: Exception) {
                APIResponse.value = APIResult.Failure("Recipes cannot be retrieved")
            }

        } else {
            APIResponse.value = APIResult.Failure("Cannot connect to the internet")
        }
    }

    private fun APIResponseHandler(response: Response<TvShowJsonData>): APIResult<TvShowJsonData>? {
        when {
            response.isSuccessful -> {
                return APIResult.Success(Recipeshavebeenretrieved)
            }

            response.message().toString().isNullOrEmpty() -> {
                return APIResult.Failure("Recipes cannot be retrieved")
            }

            response.message().toString().contains("timeout") -> {
                return APIResult.Failure("Connection time has ran out.")
            }

            response.code() == 402 -> {
                return APIResult.Failure("API key has been Limited.")
            }

            else -> {
                return APIResult.Failure(response.message())
            }
        }

    }
}