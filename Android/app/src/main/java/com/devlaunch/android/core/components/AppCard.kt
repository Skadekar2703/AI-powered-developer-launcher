package com.devlaunch.android.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {

    Card(

        modifier =
            if (onClick != null)
                modifier.clickable { onClick() }
            else
                modifier,

        shape = RoundedCornerShape(22.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
        ),

        colors = CardDefaults.cardColors(

            containerColor = MaterialTheme.colorScheme.surface

        )

    ) {

        content()

    }

}