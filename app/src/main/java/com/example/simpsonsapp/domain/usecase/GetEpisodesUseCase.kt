package com.example.simpsonsapp.domain.usecase

import androidx.paging.PagingData
import com.example.simpsonsapp.domain.model.Episode
import com.example.simpsonsapp.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(): Flow<PagingData<Episode>> {
        return repository.get_episodes()
    }
}
