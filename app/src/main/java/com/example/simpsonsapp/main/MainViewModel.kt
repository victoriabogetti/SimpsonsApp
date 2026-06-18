package com.example.simpsonsapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.simpsonsapp.domain.model.Episode
import com.example.simpsonsapp.domain.usecase.GetEpisodesBySeasonUseCase
import com.example.simpsonsapp.domain.usecase.GetEpisodesUseCase
import com.example.simpsonsapp.domain.usecase.GetSeasonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val getEpisodesBySeasonUseCase: GetEpisodesBySeasonUseCase,
    private val getSeasonsUseCase: GetSeasonsUseCase
) : ViewModel() {

    private val _selectedSeason = MutableStateFlow<Int?>(null)
    val selectedSeason: StateFlow<Int?> = _selectedSeason.asStateFlow()

    private val _availableSeasons = MutableStateFlow<List<Int>>(emptyList())
    val availableSeasons: StateFlow<List<Int>> = _availableSeasons.asStateFlow()

    val episodes: Flow<PagingData<Episode>> = _selectedSeason.flatMapLatest { season ->
        if (season == null) {
            getEpisodesUseCase()
        } else {
            getEpisodesBySeasonUseCase(season)
        }
    }.cachedIn(viewModelScope)

    init {
        loadSeasons()
    }

    private fun loadSeasons() {
        viewModelScope.launch {
            // Because the API lazy loads and seasons might only be known after pages are downloaded,
            // we will fetch available seasons from the local DB.
            // If the DB is empty, this might be empty initially.
            // We can refresh this after the first page is loaded.
            _availableSeasons.value = getSeasonsUseCase()
        }
    }

    fun onSeasonSelected(season: Int?) {
        _selectedSeason.value = season
    }

    fun refreshSeasons() {
        loadSeasons()
    }
}
