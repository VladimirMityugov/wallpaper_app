package com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chockydevelopment.wallpaperapp.presentation.util.Constants.IMAGES_TABLE

@Entity(tableName = IMAGES_TABLE)
data class CollectionItem(
    @PrimaryKey
    val id: String,
    @Embedded
    val urls: Urls,
    @Embedded
    val user: User,
    @Embedded
    val links: Links
)