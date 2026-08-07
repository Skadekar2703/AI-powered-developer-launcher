package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.AppCard

data class ActivityItem(
    val title: String,
    val timeLabel: String
)

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

            style = MaterialTheme.typography.titleMedium,

            fontWeight = FontWeight.Bold,

            color = MaterialTheme.colorScheme.onBackground

        )

        Spacer(modifier = Modifier.height(12.dp))

        AppCard(

            modifier = Modifier.fillMaxWidth()

        ) {

            Column {


                activities.forEachIndexed { index, item ->

                    Row(

                        modifier = Modifier.fillMaxWidth()
                            .padding(15.dp),

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Box(

                            modifier = Modifier
                                .size(38.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                    CircleShape
                                ),

                            contentAlignment = Alignment.Center

                        ) {

                            Icon(

                                imageVector = Icons.Outlined.History,

                                contentDescription = null,

                                tint = MaterialTheme.colorScheme.primary,

                                modifier = Modifier.size(18.dp)

                            )

                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(

                            modifier = Modifier.weight(1f)

                        ) {

                            Text(

                                text = item.title,

                                style = MaterialTheme.typography.bodyLarge,

                                fontWeight = FontWeight.SemiBold,

                                color = MaterialTheme.colorScheme.onSurface

                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(

                                text = item.timeLabel,

                                style = MaterialTheme.typography.bodySmall,

                                color = MaterialTheme.colorScheme.onSurfaceVariant

                            )

                        }

                    }

                    if (index != activities.lastIndex) {

                        Spacer(modifier = Modifier.height(14.dp))

                        HorizontalDivider(

                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.20f)

                        )

                        Spacer(modifier = Modifier.height(14.dp))

                    }

                }

            }

        }

    }

}