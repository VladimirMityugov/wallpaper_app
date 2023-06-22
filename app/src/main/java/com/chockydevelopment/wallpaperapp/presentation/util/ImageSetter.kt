package com.chockydevelopment.wallpaperapp.presentation.util


import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageSetter @Inject constructor() {

    private val _image = MutableStateFlow<CollectionItemM?>(null)
    val image = _image.asStateFlow()

    fun setImage(item: CollectionItemM){
            _image.value = item
    }
}