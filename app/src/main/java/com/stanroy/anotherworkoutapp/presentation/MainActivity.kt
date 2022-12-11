package com.stanroy.anotherworkoutapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.stanroy.anotherworkoutapp.data.DataStoreKeys
import com.stanroy.anotherworkoutapp.data.readDataStoreValue
import com.stanroy.anotherworkoutapp.domain.model.Screen
import com.stanroy.anotherworkoutapp.presentation.main_screen.MainScreen
import com.stanroy.anotherworkoutapp.presentation.onboarding_screen.OnboardingScreen
import com.stanroy.anotherworkoutapp.presentation.ui.theme.AnotherWorkoutAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: DataStore<Preferences>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            var isOnboardingFinished by remember { mutableStateOf<Boolean?>(null) }

            AnotherWorkoutAppTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = MaterialTheme.colors.background)
                val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        readDataStoreValue(dataStore, DataStoreKeys.finishedOnboarding).collect {
                            isOnboardingFinished = it ?: false
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    isOnboardingFinished?.let { onboardingFinished ->
                        NavHost(
                            navController = navController,
                            startDestination = if (onboardingFinished) Screen.MainScreen.route else Screen.OnboardingScreen.route
                        ) {
                            composable(Screen.OnboardingScreen.route) {

                                OnboardingScreen(navController = navController, dataStore)
                            }
                            composable(Screen.MainScreen.route) {
                                MainScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}