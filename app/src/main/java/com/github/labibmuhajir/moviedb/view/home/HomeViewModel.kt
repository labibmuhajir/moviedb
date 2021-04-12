package com.github.labibmuhajir.moviedb.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.labibmuhajir.moviedb.data.MovieUseCase
import com.github.labibmuhajir.moviedb.service.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModel() {
    private val _segmentedLiveData = MutableLiveData<List<Pair<String, List<Movie>>>>()
    val segmentedLiveData: LiveData<List<Pair<String, List<Movie>>>> get() = _segmentedLiveData
    private val _bannerLiveData = MutableLiveData<List<Movie>>()
    val bannerLiveData: LiveData<List<Movie>> get() = _bannerLiveData

    fun initData() {
        viewModelScope.launch {
            async { getBanner() }
            async { getSegmented() }
        }
    }

    fun getBanner() {
        viewModelScope.launch {
            try {
                val result = movieUseCase.discoverMovie().take(3)
                _bannerLiveData.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSegmented() {
        viewModelScope.launch {
            try {
                supervisorScope {
                    val result = mutableListOf<Pair<String, List<Movie>>>()
                    val popular = async { movieUseCase.getPopularMovie().take(10) }
                    val upcoming = async { movieUseCase.getUpcomingMovie().take(10) }

                    popular.await().let {
                        result.add("Popular Movies" to it)
                    }
                    upcoming.await().let {
                        result.add("Upcoming Movies" to it)
                    }

                    _segmentedLiveData.postValue(result)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}