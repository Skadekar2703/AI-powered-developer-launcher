package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devlaunch.android.navigation.BottomNavItem
import com.devlaunch.android.ui.theme.DevBackground
import com.devlaunch.android.ui.theme.DevPrimary
import com.devlaunch.android.ui.theme.DevTextMuted

@Composable
fun HomeBottomBar(

    selectedRoute: String,

    onItemClick: (BottomNavItem) -> Unit

) {

    val items = listOf(

        BottomNavItem.Home,
        BottomNavItem.Projects,
        BottomNavItem.AI,
        BottomNavItem.Notifications,
        BottomNavItem.Profile

    )

    NavigationBar(

        modifier = Modifier.navigationBarsPadding(),

        containerColor = DevBackground

    ) {

        items.forEach { item ->

            NavigationBarItem(

                selected = selectedRoute == item.route,

                onClick = {

                    onItemClick(item)

                },

                icon = {

                    Icon(

                        imageVector = item.icon,

                        contentDescription = item.title

                    )

                },

                label = {

                    Text(text = item.title)

                },

                colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = DevPrimary,

                    selectedTextColor = DevPrimary,

                    indicatorColor = DevPrimary.copy(alpha = 0.15f),

                    unselectedIconColor = DevTextMuted,

                    unselectedTextColor = DevTextMuted

                )

            )

        }

    }

}