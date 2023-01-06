package com.stanroy.anotherworkoutapp.presentation.onboarding_screen.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.stanroy.anotherworkoutapp.R
import com.stanroy.anotherworkoutapp.presentation.common.*
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.OnboardingPageIndicators
import com.stanroy.anotherworkoutapp.presentation.ui.theme.AnotherWorkoutAppTypography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SecondPageContent(pagerState: PagerState) {
    val localDensity = LocalDensity.current
    var bottomColumnHeight by remember { mutableStateOf(0.dp) }
    val rolloutBottomBar by remember {
        derivedStateOf {
            pagerState.currentPage == 1
        }
    }

    AnimatedVisibility(
        visible = rolloutBottomBar,
        enter = slideIn(tween(delayMillis = 200), initialOffset = {
            IntOffset(x = 0, y = 1200)
        }), exit = slideOut(targetOffset = {
            IntOffset(x = 0, y = 0)
        })
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.dumbbell_straight_filled_182),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .onGloballyPositioned {
                        bottomColumnHeight = with(localDensity) { it.size.height.toDp() }
                    }
                    .paint(
                        painterResource(id = R.drawable.bg_on_screen_2),
                        contentScale = ContentScale.FillWidth
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = spacing_48,
                        start = spacing_8,
                        end = spacing_8
                    ),
                    text = OnboardingDictionary.description_title,
                    style = AnotherWorkoutAppTypography.blackTitleUiC
                )
                Text(
                    modifier = Modifier.padding(
                        start = spacing_32,
                        end = spacing_32
                    ),
                    text = OnboardingDictionary.description_message,
                    style = AnotherWorkoutAppTypography.blackMessageUiC
                )
                OnboardingPageIndicators(
                    pagerState = pagerState,
                    modifier = Modifier
                )


            }
        }
    }
}