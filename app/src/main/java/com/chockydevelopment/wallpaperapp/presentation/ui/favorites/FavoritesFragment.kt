package com.chockydevelopment.wallpaperapp.presentation.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chockydevelopment.wallpaperapp.domain.local.models.FavoritesM
import com.chockydevelopment.wallpaperapp.presentation.theme.AppTheme
import com.chockydevelopment.wallpaperapp.presentation.view_models.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.primary) {
                    FavoritesList(viewModel = viewModel)
                }
            }
        }

        return composeView
    }

    @Composable
    fun FavoritesItem(favorite: FavoritesM) {
        Card(
            elevation = 4.dp,
            backgroundColor = MaterialTheme.colors.onSurface
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = "ID",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                    Text(
                        text = "${favorite.favoritesId}",
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .width(90.dp)
                ) {
                    Text(
                        text = "NAME",
                        style = MaterialTheme.typography.h6,
                        color = Color.Yellow
                    )
                    Text(
                        text = favorite.favoritesName,
                        color = Color.White
                    )
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun LocationPreview() {
        FavoritesItem(favorite = FavoritesM(
            favoritesId = 1,
            favoritesName = "MyFavorite"
        ))
    }

    @Composable
    fun FavoritesList(viewModel: FavoritesViewModel) {
        val favorites = viewModel.favorites.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            this.items(
//                count = favorites.itemCount,
//                key = favorites.itemKey(),
//                contentType = favorites.itemContentType(
//                )
//            ) { index ->
//                val item = locations[index]
//                item?.let {
//                    LocationItem(location = item)
//                }
//            }
//            locations.apply {
//                when {
//                    loadState.refresh is LoadState.Loading -> {
//                        item {
//                            Box(
//                                modifier = Modifier
//                                    .fillParentMaxSize(),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                CircularProgressIndicator()
//                            }
//                        }
//                    }
//                    loadState.append is LoadState.Loading -> {
//                        item {
//                            Box(
//                                modifier = Modifier
//                                    .fillParentMaxSize(),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                CircularProgressIndicator()
//                            }
//                        }
//                    }
//                    loadState.refresh is LoadState.Error -> {
//                        val e = locations.loadState.refresh as LoadState.Error
//                        item {
//                            ErrorItem(
//                                message = e.error.localizedMessage!!,
//                                modifier = Modifier.fillParentMaxSize(),
//                                onClickRetry = { retry() }
//                            )
//                        }
//                    }
//                }
//            }
        }
    }

    @Composable
    private fun ErrorItem(message: String, modifier: Modifier, onClickRetry: () -> Unit) {
        Box(
            modifier = modifier
                .padding(25.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message)
                Button(onClick = onClickRetry) {
                    Text(text = "RETRY")
                }
            }
        }
    }

}