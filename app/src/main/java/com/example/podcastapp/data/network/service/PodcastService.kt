package com.example.podcastapp.data.network.service

import com.example.podcastapp.data.network.model.PodcastSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastService {

    @GET("search")
    suspend fun searchPodcasts(
        @Query("q") query: String
    ) : PodcastSearchDto

}