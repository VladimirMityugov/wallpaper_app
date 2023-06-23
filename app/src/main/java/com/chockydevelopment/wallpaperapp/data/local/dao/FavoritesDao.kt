package com.chockydevelopment.wallpaperapp.data.local.dao

import androidx.room.*
import com.chockydevelopment.wallpaperapp.data.local.models_local.Favorites
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM Images_table ")
    fun getAllFavorites(): Flow<List<CollectionItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(vararg collectionItem: CollectionItem)

    @Query("DELETE FROM Images_table WHERE id =:imageId")
    suspend fun deleteFromFavoritesById(imageId: String)

}