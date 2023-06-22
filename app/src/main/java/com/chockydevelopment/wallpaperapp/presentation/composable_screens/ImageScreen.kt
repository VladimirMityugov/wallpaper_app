package com.chockydevelopment.wallpaperapp.presentation.composable_screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.presentation.util.LoadImage


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ImageScreen(imageItem: CollectionItemM) {
    ImageItem(item = imageItem)
}

@Composable
fun ImageItem(
    item: CollectionItemM
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 53.dp, bottom = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        LoadImage(url = item.urlsM.full, name = item.id)

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


//                Image(painter = painterResource(
//                    id = if (inFavorites) {
//                        R.drawable.heart_filled
//                    } else R.drawable.heart
//                ),
//                    contentDescription = "to_favorites",
//                    modifier = Modifier
//                        .padding(end = 10.dp)
//                        .size(15.dp)
//                        .clickable {
//                            if (inFavorites) viewModel.deleteFromFavoritesById(item.id) else viewModel.addToFavorites(
//                                favoritesM = FavoritesM(
//                                    favoritesId = item.id,
//                                    favoritesUrl = item.urlsM.small
//                                )
//                            )
//                        })
            }
        }
    }

}