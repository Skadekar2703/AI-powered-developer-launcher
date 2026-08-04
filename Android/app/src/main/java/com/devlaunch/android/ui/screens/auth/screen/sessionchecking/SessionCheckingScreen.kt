package com.devlaunch.android.ui.screens.auth.screen.sessionchecking

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.DevLogo
import com.devlaunch.android.ui.theme.DevPrimary
import com.devlaunch.android.ui.theme.DevTextPrimary
import com.devlaunch.android.ui.theme.DevTextSecondary
import com.devlaunch.android.ui.screens.auth.components.AuthBackground

@Composable
fun SessionCheckingScreen(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    AuthBackground(modifier = modifier, cardWidth = 360) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DevLogo(
                size = 64.dp,
                modifier = Modifier.scale(scale)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "DevLaunch",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = DevTextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Checking session...",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = DevTextSecondary,
                    fontSize = 13.sp
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.5.dp,
                color = DevPrimary
            )
        }
    }
}