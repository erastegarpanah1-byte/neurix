package com.neurix.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIntoContainer
import androidx.compose.animation.slideOutOfContainer
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.neurix.feature.chat.presentation.ChatScreen
import com.neurix.feature.home.presentation.HomeScreen
import com.neurix.feature.settings.presentation.SettingsScreen
import com.neurix.feature.settings.presentation.SettingsDetailScreen
import com.neurix.core.navigation.Screen
@Composable
fun NeurixNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) +
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(300)) +
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
        }
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToChat = {
                    navController.navigate(Screen.Chat.route)
                }
            )
        }

        composable(route = Screen.Chat.route) {
            ChatScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onNavigateToDetail = { feature ->
                    navController.navigate(feature.route)
                }
            )
        }

        composable(route = Screen.Theme.route) {
            SettingsDetailScreen(
                featureName = "Theme",
                description = "Personalize the look and feel of Neurix with custom themes, colors, and dark mode options.",
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Language.route) {
            SettingsDetailScreen(
                featureName = "Language",
                description = "Choose your preferred language for the Neurix interface and responses.",
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Voice.route) {
            SettingsDetailScreen(
                featureName = "Voice",
                description = "Configure voice input and output settings including speech recognition and text-to-speech.",
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Memory.route) {
            SettingsDetailScreen(
                featureName = "Memory",
                description = "Manage what Neurix remembers about you across conversations.",
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.Permissions.route) {
            SettingsDetailScreen(
                featureName = "Permissions",
                description = "Control which system features and data Neurix can access on your device.",
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.About.route) {
            SettingsDetailScreen(
                featureName = "About",
                description = "Neurix — Your intelligent AI companion. Built with care for a seamless experience.",
                onBack = { navController.popBackStack() }
            )
        }
    }
}
