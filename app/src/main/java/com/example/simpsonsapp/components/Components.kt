package com.example.simpsonsapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.simpsonsapp.domain.model.Episode

@Composable
fun EpisodeCard(
    episode: Episode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDetailMode: Boolean = false
) {
    Card(
        modifier = modifier
            .width(if (isDetailMode) 300.dp else 250.dp)
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = "https://thesimpsonsapi.com${episode.imagePath}",
                contentDescription = episode.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isDetailMode) 250.dp else 150.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = episode.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = if (isDetailMode) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = episode.synopsis,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isDetailMode) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Airdate: ${episode.airdate}",
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "S${episode.season}E${episode.episodeNumber}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun SeasonSelector(
    seasons: List<Int>,
    selectedSeason: Int?,
    onSeasonSelected: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp)
    ) {
        FilterChip(
            selected = selectedSeason == null,
            onClick = { onSeasonSelected(null) },
            label = { Text("All Seasons") },
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        seasons.forEach { season ->
            FilterChip(
                selected = selectedSeason == season,
                onClick = { onSeasonSelected(season) },
                label = { Text("Season $season") },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}
