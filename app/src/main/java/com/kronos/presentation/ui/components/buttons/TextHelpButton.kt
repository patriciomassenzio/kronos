package com.kronos.presentation.ui.components.buttons

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextHelpButton(text: String, onClick: () -> Unit, modifier: Modifier) {
    TextButton(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text)
    }
}