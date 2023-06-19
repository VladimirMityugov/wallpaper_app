package com.chockydevelopment.wallpaperapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.chockydevelopment.wallpaperapp.data.local.ImagesDataBase
import com.chockydevelopment.wallpaperapp.data.local.dao.FavoritesDao
import com.chockydevelopment.wallpaperapp.data.remote.LoggingInterceptor
import com.chockydevelopment.wallpaperapp.data.remote.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUnsplashApi():UnsplashApi{
        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .client(LoggingInterceptor().client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImagesDataBase(@ApplicationContext context: Context): ImagesDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = ImagesDataBase::class.java,
            name = "ImagesDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoritesDao(dataBase: ImagesDataBase): FavoritesDao {
        return dataBase.favoritesDao()
    }

}