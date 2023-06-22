package com.chockydevelopment.wallpaperapp.data.remote.mappers_remote

import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.Urls
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.UrlsM

class CollectionMapper {

    fun toCollectionItemM(collectionItem: CollectionItem): CollectionItemM {
        return CollectionItemM(
            id = collectionItem.id,
            urlsM = toUrlsM(collectionItem.urls)
        )
    }

    private fun toUrlsM(urls: Urls): UrlsM {
        return UrlsM(
            full = urls.full,
            regular = urls.regular,
            small = urls.small,
            small_s3 = urls.small_s3
        )
    }

}