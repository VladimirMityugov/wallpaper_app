package com.chockydevelopment.wallpaperapp.domain.local.repositories


import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import kotlinx.coroutines.flow.Flow

interface RepositoryLocal {

    fun getAllFavorites(): Flow<List<FavoritesM>>

    suspend fun addToFavorites(favoritesM: FavoritesM)

    suspend fun deleteFromFavoritesById(favoritesId: String)

}