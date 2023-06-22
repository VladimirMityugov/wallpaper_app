package com.chockydevelopment.wallpaperapp.presentation.composable_screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chockydevelopment.wallpaperapp.R
import com.chockydevelopment.wallpaperapp.domain.remote.models.collection.CollectionItemM
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.Screen
import com.chockydevelopment.wallpaperapp.presentation.view_models.CollectionViewModel


@Composable
fun CollectionScreen(collectionId: String, navController: NavController) {

    val viewModel = hiltViewModel<CollectionViewModel>()
    CollectionList(viewModel = viewModel, id = collectionId, navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollectionList(viewModel: CollectionViewModel, id: String, navController: NavController) {

    val dataFlow = remember { viewModel.getAllImages(id) }
    val collection = dataFlow.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 2.dp,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 53.dp, bottom = 60.dp),
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
                            navController.navigate(Screen.Image.withArgs(item.id))
                        })
                }
            }
        }
    )
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
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LoadCollectionPicture(url = item.urlsM.small, name = item.id)
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Photo by ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(item.userM.username)
                        }
                        append(" on Unsplash")
                    },
                    color = Color.White,
                    fontSize = MaterialTheme.typography.caption.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {
                        val browserIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("${item.userM.links.html}?utm_source=chocky_devs_images&utm_medium=referral")
                        )
                        startActivity(context, browserIntent, null)
                    }
                )
            }
        }
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
    ) {
        it.placeholder(R.drawable.picture)
    }
}