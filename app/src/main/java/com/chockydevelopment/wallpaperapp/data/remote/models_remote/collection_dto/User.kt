package com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto

import androidx.room.Embedded

@kotlinx.serialization.Serializable
data class User(
    val username: String,
    @Embedded
    val links:UserLinks
)
