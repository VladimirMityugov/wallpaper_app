package com.chockydevelopment.wallpaperapp.data.remote.repositories_remote

import android.util.Log
import com.chockydevelopment.wallpaperapp.data.remote.UnsplashApi
import com.chockydevelopment.wallpaperapp.data.remote.mappers_remote.CategoriesMapper
import com.chockydevelopment.wallpaperapp.data.remote.mappers_remote.CollectionMapper
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CategoriesItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.repositories.RepositoryRemote
import javax.inject.Inject

private const val TAG = "REPO_"
class RepositoryRemoteImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
): RepositoryRemote {

    override suspend fun getAllCategories(page: Int): List<CategoriesItemM> {
        val mapper = CategoriesMapper()
        val result = unsplashApi.getAllCategories(page)
        Log.d(TAG, "RESULT: $result")
        return result.map { mapper.toCategoriesItemM(it) }

    }

    override suspend fun getAllImages(id:String, page: Int): List<CollectionItemM> {
        val mapper = CollectionMapper()
        val result = unsplashApi.getAllImages(id,page)
        Log.d(TAG, "RESULT: $result")
        return result.map { mapper.toCollectionItemM(it) }
    }

}