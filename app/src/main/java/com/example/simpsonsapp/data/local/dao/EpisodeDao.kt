package com.example.simpsonsapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpsonsapp.data.local.entity.EpisodeEntity

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<EpisodeEntity>)

    @Query("SELECT * FROM episodes ORDER BY season ASC, episodeNumber ASC")
    fun pagingSource(): PagingSource<Int, EpisodeEntity>

    @Query("SELECT * FROM episodes WHERE season = :season ORDER BY episodeNumber ASC")
    fun pagingSourceBySeason(season: Int): PagingSource<Int, EpisodeEntity>

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeEntity?

    @Query("SELECT DISTINCT season FROM episodes ORDER BY season ASC")
    suspend fun getAvailableSeasons(): List<Int>

    @Query("DELETE FROM episodes")
    suspend fun clearAll()
}
