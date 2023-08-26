package com.example.podcastapp.di

import android.content.Context
import com.example.podcastapp.data.network.client.ListenNotesApiClient
import com.example.podcastapp.data.network.repository.PodcastRepositoryImpl
import com.example.podcastapp.data.network.service.PodcastService
import com.example.podcastapp.domain.repository.PodcastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideHttpClient(): OkHttpClient = ListenNotesApiClient.createHttpClient()

    @Provides
    @Singleton
    fun providePodcastService(
        client: OkHttpClient
    ): PodcastService = ListenNotesApiClient.createPodcastService(client)


    @Provides
    @Singleton
    fun providePodcastRepository(
        service: PodcastService
    ): PodcastRepository = PodcastRepositoryImpl(service)
}