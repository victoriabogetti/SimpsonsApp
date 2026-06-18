package com.example.simpsonsapp.domain.usecase

import com.example.simpsonsapp.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetSeasonsUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(): List<Int> {
        return repository.getAvailableSeasons()
    }
}
