package com.chockydevelopment.wallpaperapp.data.remote.mappers_remote

import com.chockydevelopment.wallpaperapp.data.remote.models_remote.collection_dto.*
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.*

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
            links = toUserLinksM(user.links)
        )
    }

    private fun toLinksM(links: Links): LinksM {
        return LinksM(
            download_location = links.download_location
        )
    }

    private fun toUserLinksM(links: UserLinks): UserLinksM {
        return UserLinksM(
            html = links.html
        )
    }

    fun fromCollectionItemM(collectionItemM: CollectionItemM): CollectionItem {
        return CollectionItem(
            id = collectionItemM.id,
            urls = fromUrlsM(collectionItemM.urlsM),
            user = fromUserM(collectionItemM.userM),
            links = fromLinksM(collectionItemM.linksM)
        )
    }

    private fun fromUrlsM(urlsM: UrlsM): Urls {
        return Urls(
            small = urlsM.small,
            full = urlsM.full
        )
    }

    private fun fromUserM(userM: UserM): User {
        return User(
            username = userM.username,
            links = fromUserLinksM(userM.links)
        )
    }

    private fun fromLinksM(linksM: LinksM): Links {
        return Links(
            download_location = linksM.download_location
        )
    }

    private fun fromUserLinksM(userLinksM: UserLinksM): UserLinks {
        return UserLinks(
            html = userLinksM.html
        )
    }

}