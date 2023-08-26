package com.example.podcastapp.data.network.repository

import com.example.podcastapp.data.network.service.PodcastService
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.util.Resource
import kotlinx.coroutines.flow.flow

class PodcastRepositoryImpl(
    private val podcastService: PodcastService
) : PodcastRepository {

    override suspend fun searchPodcasts(query: String) = flow{
        try {
            val result = Resource.Success(podcastService.searchPodcasts(query).asDomainModel())
            emit(result)
        }
        catch(exception: Exception){
            emit(Resource.Error(exception.message?: "Unexpected error occurred!"))
        }
    }

}