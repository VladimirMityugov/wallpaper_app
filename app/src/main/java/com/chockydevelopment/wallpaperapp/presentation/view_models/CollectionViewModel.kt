package com.chockydevelopment.wallpaperapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.use_cases.UseCaseRemote
import com.chockydevelopment.wallpaperapp.presentation.paging_sources.CollectionPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val useCaseRemote: UseCaseRemote
) : ViewModel() {

    fun getAllImages(id:String): Flow<PagingData<CollectionItemM>> = Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10),
            pagingSourceFactory = {
                CollectionPagingSource(
                    id = id,
                    useCaseRemote = useCaseRemote
                )
            }
        ).flow
            .cachedIn(viewModelScope)

}