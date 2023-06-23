package com.chockydevelopment.wallpaperapp.data.local.repositories_local

import com.chockydevelopment.wallpaperapp.data.local.dao.FavoritesDao
import com.chockydevelopment.wallpaperapp.data.local.mappers_local.FavoritesMapper
import com.chockydevelopment.wallpaperapp.data.remote.mappers_remote.CollectionMapper
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.local.repositories.RepositoryLocal
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryLocalImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
): RepositoryLocal {

    override fun getAllFavorites(): Flow<List<CollectionItemM>> {
        val mapper = CollectionMapper()
        val result = favoritesDao.getAllFavorites().map { allFavorites ->
            allFavorites.map { singleFavorite ->
                mapper.toCollectionItemM(singleFavorite)
            }
        }
        return result
    }

    override suspend fun addToFavorites(collectionItemM: CollectionItemM) {
        val mapper = CollectionMapper()
        favoritesDao.addToFavorites(mapper.fromCollectionItemM(collectionItemM))
    }

    override suspend fun deleteFromFavoritesById(imageId: String) {
        favoritesDao.deleteFromFavoritesById(imageId = imageId)
    }

}