package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.ui.theme.*

data class ActivityItem(val title: String, val timeLabel: String)

@Composable
fun RecentActivitySection(
    activities: List<ActivityItem> = listOf(
        ActivityItem("Logged into DevLaunch", "2 min ago"),
        ActivityItem("Created a new project", "15 min ago"),
        ActivityItem("AI generated login screen", "1 hour ago")
    )
) {
    Column {
        Text(
            text = "Recent Activity",
            color = DevTextPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .glassPanel(
                    cornerRadius = 16,
                    fillColor = MaterialTheme.colorScheme.surface
                )
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            activities.forEach { item ->
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .size(8.dp)
                            .background(DevPrimary, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column {
                        Text(text = item.title, color = DevTextPrimary, fontSize = 14.sp)
                        Text(text = item.timeLabel, color = DevTextFaint, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}