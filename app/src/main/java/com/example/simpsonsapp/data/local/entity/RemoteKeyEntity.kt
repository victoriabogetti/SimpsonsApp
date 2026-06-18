package com.example.simpsonsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
class RemoteKeyEntity(
    @PrimaryKey val episodeId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)
