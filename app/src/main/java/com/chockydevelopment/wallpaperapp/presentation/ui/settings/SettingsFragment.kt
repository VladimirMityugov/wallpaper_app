package com.chockydevelopment.wallpaperapp.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chockydevelopment.wallpaperapp.presentation.theme.AppTheme
import com.chockydevelopment.wallpaperapp.presentation.view_models.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

   private val viewModel:SettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())

        composeView.setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.primary) {

                }
            }
        }

        return composeView
    }


}