package com.example.simpsonsapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?,
    @SerializedName("pages") val pages: Int,
    @SerializedName("results") val results: List<EpisodeDto>
)

data class EpisodeDto(
    @SerializedName("id") val id: Int,
    @SerializedName("airdate") val airdate: String,
    @SerializedName("episode_number") val episodeNumber: Int,
    @SerializedName("image_path") val imagePath: String,
    @SerializedName("name") val name: String,
    @SerializedName("season") val season: Int,
    @SerializedName("synopsis") val synopsis: String
)
