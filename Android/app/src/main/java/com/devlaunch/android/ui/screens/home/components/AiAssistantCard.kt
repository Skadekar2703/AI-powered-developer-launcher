package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.AppCard
import com.devlaunch.android.ui.theme.*

@Composable
fun AIAssistantCard(
    suggestion: String = "Next step: Review the API documentation for Phoenix Project before today's standup.",
    ctaLabel: String = "Review Now",
    onClick: () -> Unit
) {

    AppCard(

        modifier = Modifier.fillMaxWidth()

    ) {

        Box(

            modifier = Modifier.fillMaxWidth()

        ) {

            // Glow Effect
            Box(

                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-40).dp)
                    .blur(60.dp)
                    .background(
                        DevSecondary.copy(alpha = 0.15f),
                        CircleShape
                    )

            )

            Row(

                modifier = Modifier.fillMaxWidth()
                    .padding(15.dp),

                verticalAlignment = Alignment.Top,

                horizontalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                Box(

                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            DevSecondaryContainer.copy(alpha = 0.25f),
                            RoundedCornerShape(14.dp)
                        ),

                    contentAlignment = Alignment.Center


                ) {

                    Icon(

                        imageVector = Icons.Filled.AutoAwesome,

                        contentDescription = null,

                        tint = DevPrimary,

                        modifier = Modifier.size(24.dp)

                    )

                }

                Column(

                    modifier = Modifier.weight(1f)

                ) {

                    Text(

                        text = "AI Suggestion",

                        color = DevPrimary,

                        fontWeight = FontWeight.Bold,

                        fontSize = 18.sp

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(

                        text = suggestion,

                        color = DevTextPrimary.copy(alpha = 0.75f),

                        fontSize = 14.sp,

                        lineHeight = 22.sp

                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(

                        onClick = onClick,

                        shape = RoundedCornerShape(12.dp),

                        colors = ButtonDefaults.buttonColors(

                            containerColor = DevPrimary,

                            contentColor = Color.White

                        )

                    ) {

                        Text(

                            text = ctaLabel,

                            fontWeight = FontWeight.SemiBold

                        )

                    }

                }

            }

        }

    }

}