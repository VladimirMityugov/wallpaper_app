package com.chockydevelopment.wallpaperapp.presentation.composable_screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.chockydevelopment.wallpaperapp.R
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.presentation.util.LoadImage
import com.chockydevelopment.wallpaperapp.presentation.view_models.FavoritesViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ImageScreen(imageItem: CollectionItemM) {

    val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
    ImageItem(item = imageItem, viewModel = favoritesViewModel)
}

@Composable
fun ImageItem(
    item: CollectionItemM,
    viewModel: FavoritesViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 53.dp, bottom = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        LoadImage(url = item.urlsM.small, name = item.id)
        val context = LocalContext.current


        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(MaterialTheme.colors.onPrimary.copy(0.45f)),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                val favoritesList = viewModel.favorites.collectAsState(initial = emptyList())
                val favoriteItem: CollectionItemM? = favoritesList.value.find { favoriteImage ->
                    favoriteImage.id == item.id
                }
                val inFavorites: Boolean = favoriteItem != null

                Image(painter = painterResource(
                    id = R.drawable.download
                ),
                    contentDescription = "download",
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
                        .size(22.dp)
                        .clickable {
                            val downloadIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(item.linksM.html)
                            )
                            startActivity(context, downloadIntent, null)
                        })

                Image(painter = painterResource(
                    id = if (inFavorites) {
                        R.drawable.heart_filled
                    } else R.drawable.heart
                ),
                    contentDescription = "to_favorites",
                    modifier = Modifier
                        .padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
                        .size(25.dp)
                        .clickable {
                            if (inFavorites) viewModel.deleteFromFavoritesById(item.id)
                            else viewModel.addToFavorites(item)
                        })
            }
        }
    }

}