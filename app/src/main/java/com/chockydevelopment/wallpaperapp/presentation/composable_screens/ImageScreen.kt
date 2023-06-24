package com.chockydevelopment.wallpaperapp.presentation.composable_screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.WallpaperManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chockydevelopment.wallpaperapp.R
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.presentation.util.LoadImage
import com.chockydevelopment.wallpaperapp.presentation.util.TopBar
import com.chockydevelopment.wallpaperapp.presentation.view_models.FavoritesViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ImageScreen(imageItem: CollectionItemM, navController: NavHostController) {

    val favoritesViewModel = hiltViewModel<FavoritesViewModel>()

    Column(
        modifier = Modifier
            .padding(top = 53.dp, bottom = 60.dp)
    ) {
        TopBar(
            onBackClicked = { navController.popBackStack() }
        )
        ImageItem(item = imageItem, viewModel = favoritesViewModel)
    }


}

@Composable
fun ImageItem(
    item: CollectionItemM,
    viewModel: FavoritesViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
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
                        .size(25.dp)
                        .clickable {
                            val downloadIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(item.linksM.html)
                            )
                            startActivity(context, downloadIntent, null)
                        })


                Image(painter = painterResource(
                    id = R.drawable.wallpaper
                ),
                    contentDescription = "set wallpaper",
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .size(25.dp)
                        .clickable {
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.SET_WALLPAPER
                                )
                                == PackageManager.PERMISSION_GRANTED
                            ) {
                                val context = context as Activity
                                Glide
                                    .with(context)
                                    .asBitmap()
                                    .load(item.urlsM.regular)
                                    .into(object : CustomTarget<Bitmap>() {
                                        override fun onResourceReady(
                                            resource: Bitmap,
                                            transition: Transition<in Bitmap>?
                                        ) {
                                            val wallpaperManager =
                                                WallpaperManager.getInstance(context)
                                            wallpaperManager.setBitmap(resource)
                                        }

                                        override fun onLoadCleared(placeholder: Drawable?) {
                                            // Not used
                                        }
                                    })
                            } else {
                                ActivityCompat.requestPermissions(
                                    context as Activity,
                                    arrayOf(Manifest.permission.SET_WALLPAPER),
                                    123
                                )
                            }


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