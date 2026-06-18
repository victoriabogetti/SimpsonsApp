package com.example.simpsonsapp.domain.repository

import androidx.paging.PagingData
import com.example.simpsonsapp.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun get_episodes(): Flow<PagingData<Episode>>
    fun getEpisodesBySeason(season: Int): Flow<PagingData<Episode>>
    suspend fun getAvailableSeasons(): List<Int>
    suspend fun getEpisodeById(id: Int): Episode?
}
