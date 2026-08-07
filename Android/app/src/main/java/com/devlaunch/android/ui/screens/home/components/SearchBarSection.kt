package com.devlaunch.android.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

                text = "Search projects, AI, files...",

                color = MaterialTheme.colorScheme.onSurfaceVariant

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

            focusedBorderColor = MaterialTheme.colorScheme.primary,

            unfocusedBorderColor = MaterialTheme.colorScheme.outline,

            focusedContainerColor = MaterialTheme.colorScheme.surface,

            unfocusedContainerColor = MaterialTheme.colorScheme.surface,

            focusedTextColor = MaterialTheme.colorScheme.onSurface,

            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,

            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,

            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,

            cursorColor = MaterialTheme.colorScheme.primary

        )

    )

}