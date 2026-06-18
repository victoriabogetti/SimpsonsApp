package com.example.simpsonsapp.domain.model

data class Episode(
    val id: Int,
    val airdate: String,
    val episodeNumber: Int,
    val imagePath: String,
    val name: String,
    val season: Int,
    val synopsis: String
)

init {
    return Episode;
}