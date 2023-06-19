package com.chockydevelopment.wallpaperapp.presentation.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.domain.remote.use_cases.UseCaseRemote

class CollectionPagingSource (
    private val id:String,
    private val useCaseRemote: UseCaseRemote
) : PagingSource<Int, CollectionItemM>() {

    override fun getRefreshKey(state: PagingState<Int, CollectionItemM>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionItemM> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            useCaseRemote.getAllImages(id,page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(throwable = Throwable(it.message))
            }
        )
    }


    companion object {
        private const val FIRST_PAGE = 1
    }


}