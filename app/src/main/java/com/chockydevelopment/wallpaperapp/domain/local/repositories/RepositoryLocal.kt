package com.chockydevelopment.wallpaperapp.domain.local.repositories


import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import kotlinx.coroutines.flow.Flow

interface RepositoryLocal {

    fun getAllFavorites(): Flow<List<CollectionItemM>>

    suspend fun addToFavorites(collectionItemM: CollectionItemM)

    suspend fun deleteFromFavoritesById(imageId: String)

}