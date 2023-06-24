package com.chockydevelopment.wallpaperapp.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chockydevelopment.wallpaperapp.R

@Composable
fun TopBar(
    onBackClicked: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.primary,
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(top= 5.dp)
                .fillMaxWidth()
                .height(30.dp)
                .background(MaterialTheme.colors.onPrimary.copy(0.45f))
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { onBackClicked() }
                    .size(30.dp)
                    .padding(start = 10.dp)
            )

        }
    }
}