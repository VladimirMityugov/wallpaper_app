package com.chockydevelopment.wallpaperapp.presentation.view_models


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private var _id = MutableStateFlow<String?>("M8jVbLbTRws")
    val id = _id.asStateFlow()

    fun setCollectionId(id: String) {
        _id.value = id
    }
}