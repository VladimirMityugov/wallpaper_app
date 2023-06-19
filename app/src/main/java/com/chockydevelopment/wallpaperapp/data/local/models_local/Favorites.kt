package com.chockydevelopment.wallpaperapp.data.local.models_local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Favorites")
data class Favorites(
    @PrimaryKey
    val favoritesId: Int,
    val favoritesName: String
)
