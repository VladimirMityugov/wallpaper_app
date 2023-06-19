package com.chockydevelopment.wallpaperapp.domain.remote.use_cases

import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CategoriesItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.repositories.RepositoryRemote
import javax.inject.Inject

class UseCaseRemote @Inject constructor(
    private val repositoryRemote: RepositoryRemote
) {

    suspend fun getAllCategories(page:Int): List<CategoriesItemM> {
        return repositoryRemote.getAllCategories(page)
    }

    suspend fun getAllImages(id:String, page:Int): List<CollectionItemM>{
        return repositoryRemote.getAllImages(id,page)
    }

}