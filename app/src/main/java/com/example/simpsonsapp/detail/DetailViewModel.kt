package com.example.simpsonsapp.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpsonsapp.domain.model.Episode
import com.example.simpsonsapp.domain.usecase.GetEpisodeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _episode = MutableStateFlow<Episode?>(null)
    val episode: StateFlow<Episode?> = _episode.asStateFlow()

    init {
        val episodeId = savedStateHandle.get<Int>("episodeId")
        episodeId?.let { id ->
            loadEpisodeDetail(id)
        }
    }

    private fun loadEpisodeDetail(id: Int) {
        viewModelScope.launch {
            _episode.value = getEpisodeDetailUseCase(id)
        }
    }
}
