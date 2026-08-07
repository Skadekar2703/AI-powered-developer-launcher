package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.AppCard

data class RecentProject(
    val name: String,
    val updatedLabel: String
)

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

            color = MaterialTheme.colorScheme.onBackground,

            fontSize = 18.sp,

            fontWeight = FontWeight.SemiBold

        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            projects.forEach { project ->

                AppCard(

                    modifier = Modifier.fillMaxWidth(),

                    onClick = {

                        onProjectClick(project)

                    }

                ) {

                    Row(

                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp),

                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Row(

                            modifier = Modifier.weight(1f),

                            verticalAlignment = Alignment.CenterVertically,

                            horizontalArrangement = Arrangement.spacedBy(14.dp)

                        ) {

                            Icon(

                                imageVector = Icons.Outlined.Inventory2,

                                contentDescription = null,

                                tint = MaterialTheme.colorScheme.primary

                            )

                            Column {

                                Text(

                                    text = project.name,

                                    color = MaterialTheme.colorScheme.onSurface,

                                    fontSize = 15.sp,

                                    fontWeight = FontWeight.SemiBold

                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(

                                    text = project.updatedLabel,

                                    color = MaterialTheme.colorScheme.onSurfaceVariant,

                                    fontSize = 12.sp

                                )

                            }

                        }

                    }

                }

            }

        }

    }

}