package com.example.podcastapp.domain.repository

import com.example.podcastapp.domain.model.PodcastSearch
import com.example.podcastapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface PodcastRepository {

    suspend fun searchPodcasts(
        query: String
    ): Flow<Resource<PodcastSearch>>

}