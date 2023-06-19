package com.chockydevelopment.wallpaperapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chockydevelopment.wallpaperapp.data.local.dao.FavoritesDao
import com.chockydevelopment.wallpaperapp.data.local.models_local.Favorites


@Database (entities = [Favorites::class], version = 1)

abstract class ImagesDataBase: RoomDatabase () {

    abstract fun favoritesDao(): FavoritesDao

}