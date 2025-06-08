package com.kronos.presentation.ui.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun Title(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 36.sp,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary


    )
}