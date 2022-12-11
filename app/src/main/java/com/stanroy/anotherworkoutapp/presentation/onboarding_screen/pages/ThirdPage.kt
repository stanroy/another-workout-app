package com.stanroy.anotherworkoutapp.presentation.onboarding_screen.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.stanroy.anotherworkoutapp.R
import com.stanroy.anotherworkoutapp.data.DataStoreKeys
import com.stanroy.anotherworkoutapp.data.saveDataStoreValue
import com.stanroy.anotherworkoutapp.presentation.common.*
import com.stanroy.anotherworkoutapp.presentation.components.PrimaryButton
import com.stanroy.anotherworkoutapp.presentation.components.SimpleTextInput
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.OnboardingPageIndicators
import com.stanroy.anotherworkoutapp.presentation.ui.theme.AnotherWorkoutAppTypography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ThirdPageContent(
    pagerState: PagerState,
    dataStore: DataStore<Preferences>,
    onSubmitButtonClick: () -> Unit
) {
    var submitButtonVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier
        .fillMaxSize(), bottomBar = {
        PageBottomBar(pagerState, submitButtonVisible) {

            saveDataStoreValue(
                coroutineScope,
                dataStore,
                DataStoreKeys.finishedOnboarding,
                true
            )

            onSubmitButtonClick()
        }
    }) {
        var nameInput by remember { mutableStateOf("") }
        submitButtonVisible = nameInput.isNotEmpty()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg_on_screen_3),
                    contentScale = ContentScale.FillWidth
                )
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = spacing_32,
                        vertical = spacing_24
                    ),
                text = OnboardingDictionary.onboarding_last_screen_title,
                style = AnotherWorkoutAppTypography.blackTitleUiC
            )

            Text(
                modifier = Modifier.padding(vertical = spacing_22),
                text = OnboardingDictionary.onboarding_name_input_title,
                style = AnotherWorkoutAppTypography.blackMessageUiC
            )
            SimpleTextInput(
                value = nameInput,
                singleLine = true,
                placeholder = OnboardingDictionary.onboarding_name_input_placeholder,
                onValueChange = { newValue ->
                    nameInput = newValue
                })
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PageBottomBar(
    pagerState: PagerState,
    isSubmitButtonVisible: Boolean,
    onSubmitButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.heightIn(min = spacing_64),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (!isSubmitButtonVisible)
            OnboardingPageIndicators(
                pagerState = pagerState
            )
        else
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = spacing_16),
                label = OnboardingDictionary.onboarding_submit_button_label,
                onClick = onSubmitButtonClick
            )
    }
}