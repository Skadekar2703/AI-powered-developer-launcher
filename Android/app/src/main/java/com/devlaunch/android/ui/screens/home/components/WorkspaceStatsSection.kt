package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.AppCard

data class WorkspaceStat(
    val label: String,
    val value: Int
)
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

    AppCard(

        modifier = Modifier.fillMaxWidth()

    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)   // 👈 इथे padding दे

        ) {

            Text(

                text = "Workspace Overview",

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.onSurface

            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceEvenly,

                verticalAlignment = Alignment.CenterVertically

            ) {

                stats.forEachIndexed { index, stat ->

                    Column(

                        modifier = Modifier.weight(1f),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(

                            text = stat.value.toString(),

                            color = MaterialTheme.colorScheme.primary,

                            fontSize = 30.sp,

                            fontWeight = FontWeight.Bold

                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(

                            text = stat.label,

                            color = MaterialTheme.colorScheme.onSurfaceVariant,

                            fontSize = 13.sp

                        )

                    }

                    if (index != stats.lastIndex) {

                        HorizontalDivider(

                            modifier = Modifier
                                .height(42.dp)
                                .width(1.dp),

                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)

                        )

                    }

                }

            }

        }

    }

}