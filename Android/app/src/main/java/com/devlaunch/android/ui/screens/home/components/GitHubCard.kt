package com.devlaunch.android.ui.screens.home.components

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.ui.theme.*

@Composable
fun GitHubCard(
    onClick: () -> Unit


) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .glassPanel(
                cornerRadius = 16,
                fillColor = MaterialTheme.colorScheme.surface
            )
            .clickable(onClick = onClick)
            .padding(18.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Code,
            contentDescription = null,
            tint = DevPrimary
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "GitHub Integration",
                color = DevTextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = "Connect and manage your repositories",
                color = DevTextMuted,
                fontSize = 12.sp
            )
        }

        Icon(
            imageVector = Icons.Outlined.ArrowForward,
            contentDescription = null,
            tint = DevPrimary
        )
    }
}