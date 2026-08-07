package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.AppCard
import com.devlaunch.android.ui.theme.*

@Composable
fun GitHubCard(
    onClick: () -> Unit
) {

    AppCard(

        modifier = Modifier.fillMaxWidth(),

        onClick = onClick

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(

                imageVector = Icons.Outlined.Code,

                contentDescription = null,

                tint = DevPrimary

            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(

                modifier = Modifier.weight(1f)

            ) {

                Text(

                    text = "GitHub Integration",

                    color = DevTextPrimary,

                    fontWeight = FontWeight.Bold,

                    fontSize = 16.sp

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(

                    text = "Connect and manage your repositories",

                    color = DevTextPrimary,

                    fontSize = 13.sp

                )

            }

            Icon(

                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,

                contentDescription = null,

                tint = DevPrimary

            )

        }

    }

}