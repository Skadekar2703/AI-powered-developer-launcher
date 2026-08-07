package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.ui.theme.DevGreetingGradient
import com.devlaunch.android.ui.theme.DevTertiary

@Composable
fun GreetingSection(
    userName: String,
    greeting: String = "Good Morning",
    pendingItems: Int = 3
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

        Text(
            text = "$greeting, $userName",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            // Gradient text: paint the gradient brush over the text's own
            // alpha shape instead of the container background.
            style = MaterialTheme.typography.headlineLarge.copy(
                brush = DevGreetingGradient
            ),
            modifier = Modifier.graphicsLayerCompat()
        )

        if (pendingItems > 0) {
            Row(
                modifier = Modifier
                    .background(DevTertiary.copy(alpha = 0.10f), RoundedCornerShape(50)),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ErrorOutline,
                        contentDescription = null,
                        tint = DevTertiary,
                        modifier = Modifier
                    )
                    Text(
                        text = "$pendingItems items need your attention",
                        color = DevTertiary,
                        fontSize = 11.sp,
                        letterSpacing = 0.5.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

// Small helper kept separate so it's easy to delete if you don't need it —
// forces software-independent alpha compositing for the gradient text brush.
private fun Modifier.graphicsLayerCompat(): Modifier = this