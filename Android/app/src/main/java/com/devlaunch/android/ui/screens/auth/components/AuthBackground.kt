package com.devlaunch.android.ui.screens.auth.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devlaunch.android.ui.theme.DevBackground
import com.devlaunch.android.ui.theme.DevBorder
import com.devlaunch.android.ui.theme.DevSurface

@Composable
fun AuthBackground(
    modifier: Modifier = Modifier,
    cardWidth: Int = 420,
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DevBackground),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x228B5CF6),
                        Color.Transparent
                    ),
                    center = Offset(size.width * 0.85f, size.height * 0.15f),
                    radius = size.width * 0.50f
                ),
                center = Offset(size.width * 0.85f, size.height * 0.15f),
                radius = size.width * 0.50f
            )

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0x1A3B82F6),
                        Color.Transparent
                    ),
                    center = Offset(size.width * 0.15f, size.height * 0.85f),
                    radius = size.width * 0.50f
                ),
                center = Offset(size.width * 0.15f, size.height * 0.85f),
                radius = size.width * 0.50f
            )

        }

        Box(
            modifier = Modifier
                .padding(24.dp)
                .widthIn(max = cardWidth.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(DevSurface)
                .border(
                    width = 1.dp,
                    color = DevBorder,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {

            content()

        }

    }

}