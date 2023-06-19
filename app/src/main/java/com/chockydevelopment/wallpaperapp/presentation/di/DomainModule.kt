package com.chockydevelopment.wallpaperapp.presentation.di

import com.chockydevelopment.wallpaperapp.data.local.dao.FavoritesDao
import com.chockydevelopment.wallpaperapp.data.local.repositories_local.RepositoryLocalImpl
import com.chockydevelopment.wallpaperapp.data.remote.UnsplashApi
import com.chockydevelopment.wallpaperapp.data.remote.repositories_remote.RepositoryRemoteImpl
import com.chockydevelopment.wallpaperapp.domain.local.repositories.RepositoryLocal
import com.chockydevelopment.wallpaperapp.domain.remote.repositories.RepositoryRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideRepositoryRemote(unsplashApi: UnsplashApi):RepositoryRemote{
        return RepositoryRemoteImpl(unsplashApi)
    }

    @Provides
    @ViewModelScoped
    fun provideRepositoryLocal(favoritesDao: FavoritesDao): RepositoryLocal {
        return RepositoryLocalImpl(favoritesDao)
    }

}