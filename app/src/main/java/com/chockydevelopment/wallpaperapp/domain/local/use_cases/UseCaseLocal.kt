package com.chockydevelopment.wallpaperapp.domain.local.use_cases

import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.local.repositories.RepositoryLocal
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseLocal @Inject constructor(
    private val repositoryLocal: RepositoryLocal
) {

    fun getAllFavorites(): Flow<List<CollectionItemM>>{
       return repositoryLocal.getAllFavorites()
    }

    suspend fun addToFavorites(collectionItemM: CollectionItemM){
        repositoryLocal.addToFavorites(collectionItemM)
    }

    suspend fun deleteFromFavoritesById(imageId: String){
        repositoryLocal.deleteFromFavoritesById(imageId = imageId)
    }

}