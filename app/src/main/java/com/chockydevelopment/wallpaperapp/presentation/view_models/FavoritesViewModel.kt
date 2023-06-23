package com.chockydevelopment.wallpaperapp.presentation.view_models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.local.use_cases.UseCaseLocal
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCaseLocal: UseCaseLocal
) : ViewModel() {

    val favorites: Flow<List<CollectionItemM>> = useCaseLocal.getAllFavorites()

    fun addToFavorites(collectionItemM: CollectionItemM){
        viewModelScope.launch {
            useCaseLocal.addToFavorites(collectionItemM)
        }
    }

    fun deleteFromFavoritesById(imageId: String){
        viewModelScope.launch {
            useCaseLocal.deleteFromFavoritesById(imageId)
        }
    }

}