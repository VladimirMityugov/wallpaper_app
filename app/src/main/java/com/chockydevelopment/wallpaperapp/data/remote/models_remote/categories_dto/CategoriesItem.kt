package com.chockydevelopment.wallpaperapp.data.remote.models_remote.categories_dto

data class CategoriesItem(
    val cover_photo: CoverPhoto,
    val id: String,
    val title: String,
    val total_photos: Int
)