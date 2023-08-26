package com.example.podcastapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.podcastapp.ui.home.components.EpisodeItem
import com.example.podcastapp.util.Resource


@Composable
fun HomeScreen() {

    val viewModel : HomeViewModel = hiltViewModel()
    val result = viewModel.podcastSearch.collectAsStateWithLifecycle().value
    val query = viewModel.query.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Search podcasts",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = query,
            onValueChange = {viewModel.onQueryChanged(it)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        )
        when(result){
            is Resource.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Text(
                        text = result.message,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }

            }
            is Resource.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            is Resource.Success -> {
                val podcasts = result.data.results
                if(podcasts.isEmpty()){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Text(
                            text = "No podcasts found :(",
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                else{
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(result.data.results) { episode ->
                            EpisodeItem(episode = episode)
                        }
                    }
                }
            }
        }
    }

}