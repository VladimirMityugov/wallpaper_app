package com.chockydevelopment.wallpaperapp.domain.remote.models.collection

data class CollectionItemM(
    val id: String,
    val urlsM:UrlsM,
    val userM:UserM,
    val linksM: LinksM
)