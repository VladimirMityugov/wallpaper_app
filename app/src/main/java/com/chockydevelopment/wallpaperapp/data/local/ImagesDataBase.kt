package com.chockydevelopment.wallpaperapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chockydevelopment.wallpaperapp.data.local.dao.FavoritesDao
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem


@Database (entities = [CollectionItem::class], version = 1)

abstract class ImagesDataBase: RoomDatabase () {

    abstract fun favoritesDao(): FavoritesDao

}