package com.chockydevelopment.wallpaperapp.presentation.util

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chockydevelopment.wallpaperapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoadImage(
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
        contentScale = ContentScale.Crop
    ) {
        it.placeholder(R.drawable.picture).centerCrop()
    }
}