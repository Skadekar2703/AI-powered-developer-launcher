package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.AppCard
import com.devlaunch.android.ui.theme.*

@Composable
fun QuickActionsSection(
    onAddTask: () -> Unit,
    onNewProject: () -> Unit,
    onAiChat: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        QuickActionTile(
            title = "Add Task",
            icon = Icons.Outlined.AddTask,
            iconTint = DevPrimary,
            modifier = Modifier.weight(1f),
            onClick = onAddTask
        )

        QuickActionTile(
            title = "New Project",
            icon = Icons.Outlined.FolderOpen,
            iconTint = DevTertiary,
            modifier = Modifier.weight(1f),
            onClick = onNewProject
        )

        QuickActionTile(
            title = "AI Chat",
            icon = Icons.Outlined.ChatBubbleOutline,
            iconTint = DevSecondary,
            modifier = Modifier.weight(1f),
            onClick = onAiChat
        )

    }

}

@Composable
private fun QuickActionTile(
    title: String,
    icon: ImageVector,
    iconTint: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    AppCard(

        modifier = modifier,

        onClick = onClick

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {

            Icon(

                imageVector = icon,

                contentDescription = title,

                tint = iconTint,

                modifier = Modifier.size(28.dp)

            )

            Text(

                text = title,

                color = MaterialTheme.colorScheme.onSurface,

                fontSize = 13.sp,

                fontWeight = FontWeight.Medium

            )

        }

    }

}