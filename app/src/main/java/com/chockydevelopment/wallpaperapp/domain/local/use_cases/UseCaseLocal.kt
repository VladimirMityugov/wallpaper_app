package com.chockydevelopment.wallpaperapp.domain.local.use_cases

import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.local.repositories.RepositoryLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseLocal @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {

    fun getAllFavorites(): Flow<List<FavoritesM>>{
       return repositoryLocal.getAllFavorites()
    }

    suspend fun addToFavorites(favoritesM: FavoritesM){
        repositoryLocal.addToFavorites(favoritesM)
    }

    suspend fun deleteFromFavoritesById(favoritesId: String){
        repositoryLocal.deleteFromFavoritesById(favoritesId = favoritesId)
    }

}