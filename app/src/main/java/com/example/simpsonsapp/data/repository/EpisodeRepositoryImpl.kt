package com.example.simpsonsapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.simpsonsapp.data.local.AppDatabase
import com.example.simpsonsapp.data.local.entity.EpisodeEntity
import com.example.simpsonsapp.data.remote.EpisodeRemoteMediator
import com.example.simpsonsapp.domain.model.Episode
import com.example.simpsonsapp.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val simpsonsApi: SimpsonsApi,
    private val appDatabase: AppDatabase
) : EpisodeRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getEpisodes(): Flow<PagingData<Episode>> {
        val pagingSourceFactory = { appDatabase.episodeDao().pagingSource() }
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = EpisodeRemoteMediator(simpsonsApi, appDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override fun getEpisodesBySeason(season: Int): Flow<PagingData<Episode>> {
        val pagingSourceFactory = { appDatabase.episodeDao().pagingSourceBySeason(season) }
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getAvailableSeasons(): List<Int> {
        return appDatabase.episodeDao().getAvailableSeasons()
    }

    override suspend fun getEpisodeById(id: Int): Episode? {
        return appDatabase.episodeDao().getEpisodeById(id)?.toDomain()
    }
}

fun EpisodeEntity.toDomain() = Episode(
    id = id,
    airdate = airdate,
    episodeNumber = episodeNumber,
    imagePath = imagePath,
    name = name,
    season = season,
    synopsis = synopsis
)
