package com.chockydevelopment.wallpaperapp.presentation.composable_screens

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chockydevelopment.wallpaperapp.domain.remote.models.categories.CategoriesItemM
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.Screen
import com.chockydevelopment.wallpaperapp.presentation.view_models.CategoriesViewModel

@Composable
fun CategoriesScreen(navController: NavController) {

    val viewModel = hiltViewModel<CategoriesViewModel>()
    CategoriesList(viewModel = viewModel, navController = navController)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesList(viewModel: CategoriesViewModel, navController: NavController) {

    val categories: LazyPagingItems<CategoriesItemM> =
        viewModel.allCategories.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 2.dp,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.fillMaxSize().padding(top = 53.dp, bottom = 60.dp),
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
                            navController.navigate(Screen.Collection.withArgs(item.id))
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