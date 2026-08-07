package com.devlaunch.android.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devlaunch.android.ui.screens.home.components.AIAssistantCard
import com.devlaunch.android.ui.screens.home.components.GitHubCard
import com.devlaunch.android.ui.screens.home.components.GreetingSection
import com.devlaunch.android.ui.screens.home.components.QuickActionsSection
import com.devlaunch.android.ui.screens.home.components.RecentActivitySection
import com.devlaunch.android.ui.screens.home.components.RecentProjectsSection
import com.devlaunch.android.ui.screens.home.components.SearchBarSection
import com.devlaunch.android.ui.screens.home.components.WorkspaceStatsSection
import com.devlaunch.android.ui.theme.DevBackground

@Composable
fun HomeScreen() {

    var search by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(DevBackground)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(28.dp))
            GreetingSection(
                userName = "Vishal",
                greeting = "Good Morning",
                pendingItems = 3
            )
        }

        item {
            SearchBarSection(
                value = search,
                onValueChange = { search = it }
            )
        }

        item {
            GitHubCard(onClick = { /* navigate to GitHub screen */ })
        }

        item {
            AIAssistantCard(onClick = { /* open AI assistant */ })
        }

        item {
            QuickActionsSection(
                onAddTask = { },
                onNewProject = { },
                onAiChat = { }
            )
        }

        item {
            WorkspaceStatsSection()
        }

        item {
            RecentProjectsSection(onProjectClick = { })
        }

        item {
            RecentActivitySection()
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}