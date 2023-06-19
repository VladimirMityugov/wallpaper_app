package com.chockydevelopment.wallpaperapp.presentation.view_models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.local.use_cases.UseCaseLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCaseLocal: UseCaseLocal
) : ViewModel() {

    val favorites: Flow<List<FavoritesM>> = useCaseLocal.getAllFavorites()

    fun addToFavorites(favoritesM: FavoritesM){
        viewModelScope.launch {
            useCaseLocal.addToFavorites(favoritesM)
        }
    }

    fun deleteFromFavoritesById(favoritesId: Int){
        viewModelScope.launch {
            useCaseLocal.deleteFromFavoritesById(favoritesId)
        }
    }

}