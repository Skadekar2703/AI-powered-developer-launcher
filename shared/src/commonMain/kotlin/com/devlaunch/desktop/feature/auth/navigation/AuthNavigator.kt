package com.devlaunch.desktop.feature.auth.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AuthNavigator(initialScreen: AuthScreen = AuthScreen.CheckingSession) {
    var currentScreen by mutableStateOf(initialScreen)
        private set

    private val backStack = mutableListOf<AuthScreen>()

    fun navigateTo(screen: AuthScreen, clearStack: Boolean = false) {
        if (clearStack) {
            backStack.clear()
        } else {
            // Avoid adding same screen back-to-back
            if (backStack.lastOrNull() != currentScreen) {
                backStack.add(currentScreen)
            }
        }
        currentScreen = screen
    }

    fun navigateBack(): Boolean {
        if (backStack.isNotEmpty()) {
            currentScreen = backStack.removeAt(backStack.size - 1)
            return true
        }
        return false
    }
    
    fun clearStack() {
        backStack.clear()
    }
}
