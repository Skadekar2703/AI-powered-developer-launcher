package com.devlaunch.android.ui.screens.auth.screen.welcomescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.DevButton
import com.devlaunch.android.core.components.DevLogo
import com.devlaunch.android.ui.theme.DevBorder
import com.devlaunch.android.ui.theme.DevTextPrimary
import com.devlaunch.android.ui.theme.DevTextSecondary
import com.devlaunch.android.ui.screens.auth.components.AuthBackground

@Composable
fun WelcomeScreen(
    onSignInClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AuthBackground(modifier = modifier, cardWidth = 400) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DevLogo(size = 56.dp)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome to DevLaunch",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = DevTextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The unified control center for all your software development workspace and projects.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = DevTextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            DevButton(
                text = "Sign In",
                onClick = onSignInClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onCreateAccountClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, DevBorder),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = DevTextPrimary
                )
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}