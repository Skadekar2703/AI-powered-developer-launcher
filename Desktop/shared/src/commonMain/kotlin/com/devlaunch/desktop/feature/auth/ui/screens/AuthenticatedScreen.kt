package com.devlaunch.desktop.feature.auth.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.desktop.core.components.DevButton
import com.devlaunch.desktop.core.theme.DevTextPrimary
import com.devlaunch.desktop.core.theme.DevTextSecondary
import com.devlaunch.desktop.feature.auth.model.AuthState
import com.devlaunch.desktop.feature.auth.ui.components.AuthBackground
import com.devlaunch.desktop.feature.auth.ui.components.RocketIllustration

@Composable
fun AuthenticatedScreen(
    state: AuthState,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AuthBackground(modifier = modifier, cardWidth = 440) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RocketIllustration(modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Welcome to DevLaunch!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = DevTextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            val userName = state.user?.name ?: "Developer"
            val userEmail = state.user?.email ?: "placeholder@devlaunch.com"

            Text(
                text = "Hello, $userName\n($userEmail)\n\n" +
                        "Your session is verified successfully.\n" +
                        "The application dashboard is initialized.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = DevTextSecondary,
                    lineHeight = 22.sp,
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            DevButton(
                text = "Sign Out",
                onClick = onLogoutClick
            )
        }
    }
}
