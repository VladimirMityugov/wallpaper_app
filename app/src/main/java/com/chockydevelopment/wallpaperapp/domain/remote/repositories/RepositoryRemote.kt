package com.chockydevelopment.wallpaperapp.domain.remote.repositories

import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CategoriesItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM

interface RepositoryRemote {

    suspend fun getAllCategories(page:Int): List<CategoriesItemM>

    suspend fun getAllImages(id:String, page:Int): List<CollectionItemM>

}