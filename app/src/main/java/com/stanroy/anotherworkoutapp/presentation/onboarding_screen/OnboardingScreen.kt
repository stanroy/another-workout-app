package com.stanroy.anotherworkoutapp.presentation.onboarding_screen

import android.content.pm.ActivityInfo
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.stanroy.anotherworkoutapp.domain.model.Screen
import com.stanroy.anotherworkoutapp.presentation.MainActivity
import com.stanroy.anotherworkoutapp.presentation.common.lockScreenOrientation

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController, dataStore: DataStore<Preferences>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pagerState = rememberPagerState(0)

        lockScreenOrientation(
            (LocalContext.current as MainActivity),
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        )

        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            HorizontalPager(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                count = OnboardingPage.pageCount,
                state = pagerState
            ) { page ->
                OnboardingPage.LoadOnboardingPages(
                    currentPageNumber = page,
                    pagerState = pagerState,
                    dataStore = dataStore
                ) {
                    navController.navigate(Screen.MainScreen.route)
                }
            }
        }
    }
}