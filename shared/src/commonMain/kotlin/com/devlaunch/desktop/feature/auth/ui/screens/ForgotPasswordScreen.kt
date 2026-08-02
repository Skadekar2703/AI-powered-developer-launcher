package com.devlaunch.desktop.feature.auth.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.desktop.core.components.DevButton
import com.devlaunch.desktop.core.components.DevLogo
import com.devlaunch.desktop.core.components.DevTextField
import com.devlaunch.desktop.core.theme.DevPrimary
import com.devlaunch.desktop.core.theme.DevTextPrimary
import com.devlaunch.desktop.core.theme.DevTextSecondary
import com.devlaunch.desktop.feature.auth.model.AuthState
import com.devlaunch.desktop.feature.auth.ui.components.AuthBackground

@Composable
fun ForgotPasswordScreen(
    state: AuthState,
    onEmailChanged: (String) -> Unit,
    onResetClick: () -> Unit,
    onBackToLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AuthBackground(modifier = modifier, cardWidth = 400) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DevLogo(size = 48.dp)
            Spacer(modifier = Modifier.height(16.dp))

            if (state.forgotSuccessMessage != null) {
                // Success state
                Text(
                    text = "Check Your Email",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = DevTextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = state.forgotSuccessMessage,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DevTextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                DevButton(
                    text = "Back to Sign In",
                    onClick = onBackToLoginClick
                )
            } else {
                // Form state
                Text(
                    text = "Forgot Password",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = DevTextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Enter your email address and we'll send you a link to reset your password.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DevTextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                DevTextField(
                    value = state.forgotEmail,
                    onValueChange = onEmailChanged,
                    label = "Email Address",
                    placeholder = "name@company.com",
                    error = state.forgotEmailError,
                    enabled = !state.isLoading
                )

                if (state.error != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (state.isLoading) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(DevPrimary.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = DevTextPrimary
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Sending Link...",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = DevTextPrimary,
                                fontSize = 14.sp
                            )
                        )
                    }
                } else {
                    DevButton(
                        text = "Reset Password",
                        onClick = onResetClick
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Back to Sign In",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DevPrimary,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable(onClick = onBackToLoginClick)
                )
            }
        }
    }
}
