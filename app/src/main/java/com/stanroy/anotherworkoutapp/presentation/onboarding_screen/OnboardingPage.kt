@file:OptIn(ExperimentalPagerApi::class)

package com.stanroy.anotherworkoutapp.presentation.onboarding_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.stanroy.anotherworkoutapp.R
import com.stanroy.anotherworkoutapp.presentation.common.*
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.pages.FirstPageContent
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.pages.SecondPageContent
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.pages.ThirdPageContent


sealed class OnboardingPage(val content: @Composable (pagerState: PagerState) -> Unit = {}) {
    class FirstOnboardingPage() : OnboardingPage({ FirstPageContent(it) })
    class SecondOnboardingPage() : OnboardingPage({ SecondPageContent(it) })
    class ThirdOnboardingPage(
        dataStore: DataStore<Preferences>,
        onSubmitButtonClicked: () -> Unit
    ) :
        OnboardingPage({ ThirdPageContent(it, dataStore, onSubmitButtonClicked) })

    companion object {
        const val pageCount = 3

        @Composable
        fun LoadOnboardingPages(
            currentPageNumber: Int,
            pagerState: PagerState,
            dataStore: DataStore<Preferences>,
            onSubmitButtonClicked: () -> Unit
        ) {
            when (currentPageNumber) {
                0 -> FirstOnboardingPage().content(pagerState)
                1 -> SecondOnboardingPage().content(pagerState)
                2 -> ThirdOnboardingPage(dataStore, onSubmitButtonClicked).content(pagerState)
            }
        }
    }
}


@Composable
fun OnboardingPageIndicators(modifier: Modifier = Modifier, pagerState: PagerState) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = spacing_22),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pagerState.pageCount) {
            val icon =
                if (i == pagerState.currentPage) R.drawable.kettlebell_filled_8 else R.drawable.kettlebell_stroke_8
            val scale by animateFloatAsState(targetValue = if (i == pagerState.currentPage) 1.18f else 1f)

            Image(
                modifier = Modifier
                    .scale(scale)
                    .animateContentSize(),
                painter = painterResource(id = icon),
                contentDescription = null
            )
        }
    }
}