package com.chockydevelopment.wallpaperapp.data.local.mappers_local

import com.chockydevelopment.wallpaperapp.data.local.models_local.Favorites
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM

class FavoritesMapper {

    fun fromFavoritesM(favoritesM: FavoritesM): Favorites{
        return Favorites(
            favoritesId = favoritesM.favoritesId,
            favoritesUrl = favoritesM.favoritesUrl
        )
    }

    fun toFavoritesM(favorites: Favorites):FavoritesM{
        return FavoritesM(
            favoritesId = favorites.favoritesId,
            favoritesUrl = favorites.favoritesUrl
        )
    }
}