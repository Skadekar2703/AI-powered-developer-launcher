package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.ui.theme.*

data class WorkspaceStat(val label: String, val value: Int)

@Composable
fun WorkspaceStatsSection(
    projects: Int = 12,
    tasks: Int = 41,
    teams: Int = 6
) {
    val stats = listOf(
        WorkspaceStat("Projects", projects),
        WorkspaceStat("Tasks", tasks),
        WorkspaceStat("Teams", teams)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .glassPanel(
                cornerRadius = 16,
                fillColor = MaterialTheme.colorScheme.surface
            )
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        stats.forEach { stat ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stat.value.toString(),
                    color = DevPrimary,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stat.label,
                    color = DevTextMuted,
                    fontSize = 12.sp
                )
            }
        }
    }
}