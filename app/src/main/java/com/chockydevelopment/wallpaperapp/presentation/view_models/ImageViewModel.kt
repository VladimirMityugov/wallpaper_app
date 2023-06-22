package com.chockydevelopment.wallpaperapp.presentation.view_models


import androidx.lifecycle.ViewModel
import com.chockydevelopment.wallpaperapp.presentation.util.ImageSetter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
imageSetter: ImageSetter
) : ViewModel() {

       val image = imageSetter.image

}