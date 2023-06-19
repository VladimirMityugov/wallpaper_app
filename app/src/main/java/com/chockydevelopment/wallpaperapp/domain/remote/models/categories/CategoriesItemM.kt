package com.chockydevelopment.wallpaperapp.domain.remote.models.categories

data class CategoriesItemM(
    val cover_photo: CoverPhotoM,
    val id: String,
    val title: String,
    val total_photos: Int
)