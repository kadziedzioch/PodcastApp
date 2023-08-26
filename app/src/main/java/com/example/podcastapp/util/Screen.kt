package com.example.podcastapp.util

sealed class Screen(val route: String){

    object HomeScreen: Screen("home_screen")
    object PodcastDetailScreen: Screen("podcast_detail_screen")
}
