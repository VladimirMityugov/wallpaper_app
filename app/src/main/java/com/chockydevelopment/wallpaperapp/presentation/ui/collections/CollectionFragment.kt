package com.chockydevelopment.wallpaperapp.presentation.ui.collections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.presentation.theme.AppTheme
import com.chockydevelopment.wallpaperapp.presentation.view_models.CollectionViewModel
import com.chockydevelopment.wallpaperapp.presentation.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "COLLECTION_"

@AndroidEntryPoint
class CollectionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.primary) {
                    val viewModel = viewModel<CollectionViewModel>()
                    val auxViewModel = viewModel<MainViewModel>()
                    CollectionList(viewModel = viewModel, auxViewModel = auxViewModel)
                }
            }
        }

        return composeView
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun CollectionList(viewModel: CollectionViewModel, auxViewModel: MainViewModel) {
        val id by auxViewModel.id.collectAsState()
        if (id != null) {
            val collection: LazyPagingItems<CollectionItemM> =
                viewModel.getAllImages(id!!).collectAsLazyPagingItems()
            Log.d(TAG, "ID: $id")
            Log.d(TAG, "COLLECTION: ${collection.itemCount}")

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(100.dp),
                verticalItemSpacing = 2.dp,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.fillMaxSize(),
                content = {
                    this.items(
                        count = collection.itemCount,
                        key = collection.itemKey(),
                        contentType = collection.itemContentType(
                        )
                    ) { index ->
                        val item = collection[index]
                        if (item != null) {
                            CollectionItem(item = item,
                                onClick = {
                                })
                        }
                    }
                }
            )
        }


    }


    @Composable
    fun CollectionItem(
        item: CollectionItemM,
        onClick: () -> Unit
    ) {
        Card(
            elevation = 15.dp,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                },
            shape = RoundedCornerShape(3.dp)
        ) {
            LoadCollectionPicture(url = item.urlsM.full, name = item.id)
        }
    }


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun LoadCollectionPicture(
        url: String,
        name: String
    ) {
        GlideImage(
            model = url,
            contentDescription = name,
            modifier = Modifier
                .border(
                    1.dp,
                    MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(3.dp)
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.FillBounds
        )
    }

}
