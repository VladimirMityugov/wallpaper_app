package com.chockydevelopment.wallpaperapp.data.local.repositories_local

import com.chockydevelopment.wallpaperapp.data.local.dao.FavoritesDao
import com.chockydevelopment.wallpaperapp.data.local.mappers_local.FavoritesMapper
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.local.repositories.RepositoryLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryLocalImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
): RepositoryLocal {

    override fun getAllFavorites(): Flow<List<FavoritesM>> {
        val mapper = FavoritesMapper()
        val result = favoritesDao.getAllFavorites().map { allFavorites ->
            allFavorites.map { singleFavorite ->
                mapper.toFavoritesM(singleFavorite)
            }
        }
        return result
    }

    override suspend fun addToFavorites(favoritesM: FavoritesM) {
        val mapper = FavoritesMapper()
        favoritesDao.addToFavorites(mapper.fromFavoritesM(favoritesM))
    }

    override suspend fun deleteFromFavoritesById(favoritesId: Int) {
        favoritesDao.deleteFromFavoritesById(favoritesId = favoritesId)
    }

}