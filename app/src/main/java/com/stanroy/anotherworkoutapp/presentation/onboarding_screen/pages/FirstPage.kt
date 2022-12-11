package com.stanroy.anotherworkoutapp.presentation.onboarding_screen.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.stanroy.anotherworkoutapp.R
import com.stanroy.anotherworkoutapp.presentation.common.OnboardingDictionary
import com.stanroy.anotherworkoutapp.presentation.common.spacing_24
import com.stanroy.anotherworkoutapp.presentation.common.spacing_72
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.OnboardingPageIndicators
import com.stanroy.anotherworkoutapp.presentation.ui.theme.AnotherWorkoutAppTypography


@OptIn(ExperimentalPagerApi::class)
@Composable
fun FirstPageContent(pagerState: PagerState) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dumbbell_straight_filled_182),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = spacing_72, vertical = spacing_24),
                text = OnboardingDictionary.welcome_title,
                style = AnotherWorkoutAppTypography.blackTitleUiC
            )
            Image(
                painter = painterResource(id = R.drawable.dumbbell_straight_filled_182),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .paint(
                    painterResource(id = R.drawable.bg_on_screen_1),
                    contentScale = ContentScale.FillWidth
                ),
            verticalAlignment = Alignment.Bottom,
        ) {
            OnboardingPageIndicators(
                pagerState = pagerState,
                modifier = Modifier
            )
        }
    }
}