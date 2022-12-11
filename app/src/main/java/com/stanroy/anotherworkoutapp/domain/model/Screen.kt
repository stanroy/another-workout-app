package com.stanroy.anotherworkoutapp.domain.model

sealed class Screen(val route: String) {
    object OnboardingScreen : Screen(route = "OnboardingScreen")
    object MainScreen : Screen(route = "MainScreen")
}