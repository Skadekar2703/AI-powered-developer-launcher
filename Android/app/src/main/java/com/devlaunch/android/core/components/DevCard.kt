package com.devlaunch.android.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devlaunch.android.ui.theme.DevBorder
import com.devlaunch.android.ui.theme.DevSurface


@Composable
fun DevCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(

        modifier = modifier,

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3F4F6)
        ),



        border = BorderStroke(
            1.dp,
            DevBorder.copy(alpha = 0.35f)
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(10.dp),

            content = content

        )

    }

}