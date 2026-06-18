package com.example.simpsonsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpsonsapp.data.local.dao.EpisodeDao
import com.example.simpsonsapp.data.local.dao.RemoteKeyDao
import com.example.simpsonsapp.data.local.entity.EpisodeEntity
import com.example.simpsonsapp.data.local.entity.RemoteKeyEntity

@Database(entities = [EpisodeEntity::class, RemoteKeyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
