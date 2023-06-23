package com.chockydevelopment.wallpaperapp.presentation.composable_screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chockydevelopment.wallpaperapp.R
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.presentation.util.LoadImage
import com.chockydevelopment.wallpaperapp.presentation.view_models.FavoritesViewModel

@Composable
fun FavoritesScreen(navController: NavController) {


    val favoritesViewModel = hiltViewModel<FavoritesViewModel>()

    FavoritesList(
        viewModel = favoritesViewModel
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesList(
    viewModel: FavoritesViewModel
) {

    val dataFlow = remember { viewModel.favorites }
    val collection = dataFlow.collectAsState(initial = emptyList())


    if (collection.value.isEmpty()) {
        Text(
            text = "No items found",
            modifier = Modifier.fillMaxSize()
        )
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 2.dp,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 53.dp, bottom = 60.dp)
        ) {
            this.items(collection.value) {
                FavoriteItem(item = it, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FavoriteItem(
    item: FavoritesM,
    viewModel: FavoritesViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LoadImage(url = item.favoritesUrl, name = item.favoritesId)
        val context = LocalContext.current


        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                    .background(MaterialTheme.colors.onPrimary.copy(0.45f)),
                horizontalArrangement = Arrangement.End,

                ) {
                val favoritesList = viewModel.favorites.collectAsState(initial = emptyList())
                val favoriteItem: FavoritesM? = favoritesList.value.find { favoriteImage ->
                    favoriteImage.favoritesId == item.favoritesId
                }
                val inFavorites: Boolean = favoriteItem != null


                Image(painter = painterResource(
                    id = R.drawable.delete
                ),
                    contentDescription = "to_favorites",
                    modifier = Modifier
                        .padding(end = 10.dp, top = 5.dp)
                        .size(18.dp)
                        .clickable {
                            viewModel.deleteFromFavoritesById(item.favoritesId)
                        })
            }
        }
    }

}