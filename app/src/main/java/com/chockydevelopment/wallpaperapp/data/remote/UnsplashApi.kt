package com.chockydevelopment.wallpaperapp.data.remote

import com.chockydevelopment.wallpaperapp.data.remote.models_remote.categories_dto.CategoriesItem
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID $KEY")
    @GET("topics/?")
    suspend fun getAllCategories(
        @Query("page") page: Int
    ): List<CategoriesItem>

    @Headers("Authorization: Client-ID $KEY")
    @GET("/topics/{id}/photos?")
    suspend fun getAllImages(
        @Path("id")id:String,
        @Query("page") page: Int
    ): List<CollectionItem>

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        private const val KEY = "Rwu_P3p6fvAkAcuQn9da6Ctm79WZjWs2xDJsGtL0bPg"
    }


}