package com.devlaunch.android.ui.screens.auth.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.android.core.components.DevButton
import com.devlaunch.android.core.components.DevGoogleButton
import com.devlaunch.android.core.components.DevLogo
import com.devlaunch.android.core.components.DevTextField
import com.devlaunch.android.ui.theme.DevBorder
import com.devlaunch.android.ui.theme.DevPrimary
import com.devlaunch.android.ui.theme.DevTextMuted
import com.devlaunch.android.ui.theme.DevTextSecondary
import com.devlaunch.android.ui.screens.auth.components.AuthBackground
import com.devlaunch.android.ui.screens.auth.model.AuthState
import com.devlaunch.android.ui.theme.DevTextPrimary

@Composable
fun LoginScreen(
    state: AuthState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AuthBackground(modifier = modifier, cardWidth = 420) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = state.email
            )
            DevLogo(size = 52.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = DevTextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Sign in to continue to DevLaunch.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = DevTextSecondary,
                    fontSize = 13.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            DevTextField(
                value = state.email,
                onValueChange = onEmailChanged,
                label = "Email Address",
                placeholder = "name@company.com",
                error = state.emailError,
                enabled = !state.isLoading
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                DevTextField(
                    value = state.password,
                    onValueChange = onPasswordChanged,
                    label = "Password",
                    placeholder = "••••••••",
                    isPassword = true,
                    error = state.passwordError,
                    enabled = !state.isLoading
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = DevPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        ),
                        modifier = Modifier.clickable(enabled = !state.isLoading, onClick = onForgotPasswordClick)
                    )
                }
            }

            if (state.error != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
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
                        .height(48.dp)
                        .background(DevPrimary.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = DevTextPrimary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Signing In...",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = DevTextPrimary,
                            fontSize = 14.sp
                        )
                    )
                }
            } else {
                DevButton(
                    text = "Sign In",
                    onClick = onLoginClick,
                    enabled = true
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = DevBorder
                )
                Text(
                    text = "OR",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = DevTextMuted,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.2.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = DevBorder
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            DevGoogleButton(
                onClick = onGoogleClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodyMedium.copy(color = DevTextSecondary)
                )
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = DevPrimary,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable(enabled = !state.isLoading, onClick = onCreateAccountClick)
                )
            }
        }
    }
}