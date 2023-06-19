package com.chockydevelopment.wallpaperapp.data.remote.mappers_remote

import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionUrls
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionUrlsM

class CollectionMapper {

    fun toCollectionItemM(collectionItem: CollectionItem): CollectionItemM {
        return CollectionItemM(
            id = collectionItem.id,
            collectionUrlsM = toUrlsM(collectionItem.collectionUrls)
        )
    }

    private fun toUrlsM(collectionUrls: CollectionUrls): CollectionUrlsM {
        return CollectionUrlsM(
            full = collectionUrls.full,
            regular = collectionUrls.regular,
            small = collectionUrls.small
        )
    }

}