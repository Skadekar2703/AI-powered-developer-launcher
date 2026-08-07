package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.ui.theme.*

data class RecentProject(val name: String, val updatedLabel: String)

@Composable
fun RecentProjectsSection(
    projects: List<RecentProject> = listOf(
        RecentProject("Legacy Admin Panel", "Updated yesterday"),
        RecentProject("Marketing Site v3", "Updated 3d ago")
    ),
    onProjectClick: (RecentProject) -> Unit
) {
    Column {
        Text(
            text = "Recent Projects",
            color = DevTextPrimary,
            fontSize = 16.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            projects.forEach { project ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .glassPanel(
                            cornerRadius = 16,
                            fillColor = MaterialTheme.colorScheme.surface
                        )
                        .clickable { onProjectClick(project) }
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Inventory2,
                            contentDescription = null,
                            tint = DevTextMuted
                        )
                        Text(text = project.name, color = DevTextPrimary, fontSize = 14.sp)
                    }
                    Text(text = project.updatedLabel, color = DevTextFaint, fontSize = 11.sp)
                }
            }
        }
    }
}