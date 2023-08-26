package com.example.podcastapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcastapp.domain.model.PodcastSearch
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val podcastRepository: PodcastRepository
) :ViewModel(){

    private val _query = MutableStateFlow("Android")
    val query: StateFlow<String> = _query

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val podcastSearch = _query
        .debounce(500)
        .filter {it.isNotEmpty()}
        .flatMapLatest {
            podcastRepository.searchPodcasts(it)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            Resource.Loading
        )

    fun onQueryChanged(text: String){
        _query.value = text
    }
}