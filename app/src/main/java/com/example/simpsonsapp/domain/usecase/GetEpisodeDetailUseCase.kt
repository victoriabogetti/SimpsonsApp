package com.example.simpsonsapp.domain.usecase

import com.example.simpsonsapp.domain.model.Episode
import com.example.simpsonsapp.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodeDetailUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(id: Int): Episode? {
        return repository.getEpisodeById(id)
    }
}
