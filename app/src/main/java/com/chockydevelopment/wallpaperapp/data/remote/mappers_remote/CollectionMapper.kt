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
            full = urls.full,
            regular = urls.regular
        )
    }

    private fun toUserM(user: User): UserM {
        return UserM(
            username = user.username
        )
    }

    private fun toLinksM(links: Links): LinksM {
        return LinksM(
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
            full = urlsM.full,
            regular = urlsM.regular
        )
    }

    private fun fromUserM(userM: UserM): User {
        return User(
            username = userM.username
        )
    }

    private fun fromLinksM(linksM: LinksM): Links {
        return Links(
            html = linksM.html
        )
    }

}