package com.chockydevelopment.wallpaperapp.presentation.composable_screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.chockydevelopment.wallpaperapp.R
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.Screen
import com.chockydevelopment.wallpaperapp.presentation.util.LoadImage
import com.chockydevelopment.wallpaperapp.presentation.view_models.CollectionViewModel
import com.chockydevelopment.wallpaperapp.presentation.view_models.FavoritesViewModel

private const val TAG = "COLLECTION_"

@Composable
fun CollectionScreen(collectionId: String, navController: NavController) {

    val viewModel = hiltViewModel<CollectionViewModel>()
    val favoritesViewModel = hiltViewModel<FavoritesViewModel>()

    CollectionList(
        viewModel = viewModel,
        favoritesViewModel = favoritesViewModel,
        id = collectionId,
        navController = navController
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollectionList(
    viewModel: CollectionViewModel,
    favoritesViewModel: FavoritesViewModel,
    id: String,
    navController: NavController
) {

    val dataFlow = remember { viewModel.getAllImages(id) }
    val collection = dataFlow.collectAsLazyPagingItems()


    if (collection.itemCount == 0 && collection.loadState.refresh != LoadState.Loading) {
        // Show a loading indicator or an empty state
        Text(text = "No items found")
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 2.dp,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 53.dp, bottom = 60.dp)
        ) {
            this.items(
                count = collection.itemCount,
                key = collection.itemKey(),
                contentType = collection.itemContentType(
                )
            ) { index ->
                val item = collection[index]
                if (item != null) {
                    CollectionItem(item = item,
                        viewModel = favoritesViewModel,
                        onClick = {
                            viewModel.setImage(item)
                            navController.navigate(Screen.Image.screen_route)
                        })
                }
            }
        }
    }
}

@Composable
fun CollectionItem(
    item: CollectionItemM,
    viewModel: FavoritesViewModel,
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
        val context = LocalContext.current
        val favoritesList = viewModel.favorites.collectAsState(initial = emptyList())
        val favoriteItem: CollectionItemM? = favoritesList.value.find { favoriteImage ->
            favoriteImage.id == item.id
        }
        val inFavorites: Boolean = favoriteItem != null

        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.BottomEnd
        ) {
            LoadImage(url = item.urlsM.small, name = item.id)

            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(23.dp)
                        .padding(bottom = 5.dp)
                        .background(MaterialTheme.colors.onPrimary.copy(0.45f)),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Text(
                        text = buildAnnotatedString {
                            append("by ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                                append(item.userM.username)
                            }
                            append(" on Unsplash")
                        },
                        color = Color.Black,
                        fontSize = 11.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(170.dp)
                            .padding(start = 10.dp)
                            .clickable {
                                val browserIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("${item.userM.links.html}?utm_source=chocky_devs_images&utm_medium=referral")
                                )
                                startActivity(context, browserIntent, null)
                            }
                    )

                    Image(painter = painterResource(
                        id = if (inFavorites) {
                            R.drawable.heart_filled
                        } else R.drawable.heart
                    ),
                        contentDescription = "to_favorites",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(15.dp)
                            .clickable {
                                if (inFavorites) viewModel.deleteFromFavoritesById(item.id)
                                else viewModel.addToFavorites(item)
                            })
                }
            }
        }
    }
}


