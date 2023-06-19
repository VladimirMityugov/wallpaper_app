package com.chockydevelopment.wallpaperapp.data.remote.mappers_remote

import com.chockydevelopment.wallpaperapp.data.remote.models_remote.categories_dto.CategoriesItem
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.categories_dto.CoverPhoto
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.categories_dto.Urls
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CategoriesItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CoverPhotoM
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.UrlsM

class CategoriesMapper {

   fun toCategoriesItemM(categoriesItem: CategoriesItem): CategoriesItemM {
        return CategoriesItemM(
            cover_photo = toCoverPhotoM(categoriesItem.cover_photo),
            id = categoriesItem.id,
            title = categoriesItem.title,
            total_photos = categoriesItem.total_photos
        )
    }

    private fun toCoverPhotoM(coverPhoto: CoverPhoto): CoverPhotoM {
        return CoverPhotoM(
            urlsM = toUrlsM(coverPhoto.urls)
        )
    }

    private fun toUrlsM(urls: Urls): UrlsM {
        return UrlsM(
            full = urls.full,
            regular = urls.regular,
            small = urls.small
        )
    }

}