package com.chockydevelopment.wallpaperapp.presentation.ui.collections

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chockydevelopment.wallpaperapp.R
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CategoriesItemM
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CoverPhotoM
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.UrlsM
import com.chockydevelopment.wallpaperapp.presentation.theme.AppTheme
import com.chockydevelopment.wallpaperapp.presentation.view_models.CategoriesViewModel
import com.chockydevelopment.wallpaperapp.presentation.view_models.CollectionViewModel
import com.chockydevelopment.wallpaperapp.presentation.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "CATEGORIES"

@AndroidEntryPoint
class CategoriesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.primary) {
                    val viewModel = viewModel<CategoriesViewModel>()
                    val auxViewModel = viewModel<MainViewModel>()
                    CategoriesList(viewModel = viewModel, auxViewModel = auxViewModel)
                }
            }
        }

        return composeView
    }

    @Preview(showBackground = true)
    @Composable
    fun CategoryPreview() {
        CategoriesItemM(
            cover_photo = CoverPhotoM(
                urlsM = UrlsM(
                    full = "",
                    regular = "",
                    small = ""
                )
            ),
            id = "Jpg6Kidl-Hk",
            title = "Animals",
            total_photos = 250
        )
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun CategoriesList(viewModel: CategoriesViewModel, auxViewModel: MainViewModel) {

        val categories: LazyPagingItems<CategoriesItemM> =
            viewModel.allCategories.collectAsLazyPagingItems()

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp),
            verticalItemSpacing = 2.dp,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxSize(),
            content = {
                this.items(
                    count = categories.itemCount,
                    key = categories.itemKey(),
                    contentType = categories.itemContentType(
                    )
                ) { index ->
                    val item = categories[index]
                    if (item != null) {
                        CategoryItem(category = item,
                            onClick = {
                                auxViewModel.setCollectionId(item.id)
                                findNavController().navigate(R.id.action_navigation_collections_to_collectionFragment)

                            })
                    }
                }
            }
        )
    }


    @Composable
    fun CategoryItem(
        category: CategoriesItemM,
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

            Box(modifier = Modifier.fillMaxSize()) {
                LoadPicture(url = category.cover_photo.urlsM.small, name = category.title)

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.onPrimary.copy(0.5f))
                    ) {
                        Text(
                            text = category.title,
                            modifier = Modifier.padding(top = 5.dp, start = 5.dp),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 12.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = category.total_photos.toString(),
                            modifier = Modifier.padding(top = 5.dp, end = 5.dp),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun LoadPicture(
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
