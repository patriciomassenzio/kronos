package com.kronos.presentation.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kronos.presentation.ui.theme.ButtonColors


@Preview
@Composable
fun previewButton2(){
    KronosOutlinedButton("texto", {})
}

@Composable
fun KronosOutlinedButton(
    text: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
){
    OutlinedButton(
        onClick = { onClick?.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,  // Fondo transparente para outlined button
            contentColor = ButtonColors.text     // Color del texto y borde
        ),
        border = BorderStroke(1.dp, ButtonColors.background)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}