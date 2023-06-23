package com.chockydevelopment.wallpaperapp.data.remote.mappers_remote

import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.CollectionItem
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.Links
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.Urls
import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.User
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.LinksM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.UrlsM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.UserM

class CollectionMapper {

    fun toCollectionItemM(collectionItem: CollectionItem): CollectionItemM {
        return CollectionItemM(
            id = collectionItem.id,
            urlsM = toUrlsM(collectionItem.urls),
            userM = toUserM(collectionItem.user),
            linksM = toLinksM(collectionItem.links)
        )
    }

    private fun toUrlsM(urls: Urls): UrlsM {
        return UrlsM(
            small = urls.small,
            full = urls.full
        )
    }

    private fun toUserM(user: User): UserM {
        return UserM(
            username = user.username,
            links = toLinksM(user.links)
        )
    }

    private fun toLinksM(links: Links): LinksM {
        return LinksM(
            html = links.html,
            download_location = links.download_location
        )
    }

}