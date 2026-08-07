package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devlaunch.android.ui.theme.DevBorder
import com.devlaunch.android.ui.theme.DevPrimary
import com.devlaunch.android.ui.theme.DevSurface
import com.devlaunch.android.ui.theme.DevTextMuted
import com.devlaunch.android.ui.theme.DevTextPrimary

@Composable
fun SearchBarSection(

    value: String,

    onValueChange: (String) -> Unit

) {

    OutlinedTextField(

        value = value,

        onValueChange = onValueChange,

        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),

        placeholder = {

            Text(
                "Search projects, AI, files...",
                color = DevTextMuted
            )

        },

        leadingIcon = {

            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )

        },

        singleLine = true,

        shape = RoundedCornerShape(16.dp),

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = DevPrimary,

            unfocusedBorderColor = DevBorder,

            focusedContainerColor = DevSurface,

            unfocusedContainerColor = DevSurface,

            focusedTextColor = DevTextPrimary,

            unfocusedTextColor = DevTextPrimary,

            focusedLeadingIconColor = DevPrimary,

            unfocusedLeadingIconColor = DevTextMuted

        )

    )

}