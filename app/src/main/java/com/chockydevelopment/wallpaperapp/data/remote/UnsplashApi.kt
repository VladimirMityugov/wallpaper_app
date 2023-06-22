package com.chockydevelopment.wallpaperapp.data.remote

import com.chockydevelopment.wallpaperapp.BuildConfig
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.categories_dto.CategoriesItem
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("topics/?")
    suspend fun getAllCategories(
        @Query("page") page: Int
    ): List<CategoriesItem>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("topics/{id_or_slug}/photos?")
    suspend fun getAllImages(
        @Path("id_or_slug")id:String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): List<CollectionItem>

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }


}