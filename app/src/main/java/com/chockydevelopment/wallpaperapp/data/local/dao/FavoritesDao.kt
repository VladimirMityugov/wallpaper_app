package com.chockydevelopment.wallpaperapp.data.local.dao

import androidx.room.*
import com.chockydevelopment.wallpaperapp.data.local.models_local.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM Favorites ")
    fun getAllFavorites(): Flow<List<Favorites>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(vararg favorites: Favorites)

    @Query("DELETE FROM Favorites WHERE favoritesId =:favoritesId")
    suspend fun deleteFromFavoritesById(favoritesId: String)

}