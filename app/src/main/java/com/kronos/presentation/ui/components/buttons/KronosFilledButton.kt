package com.kronos.presentation.ui.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kronos.presentation.ui.theme.ButtonColors


@Preview
@Composable
fun previewButton(){
    KronosFilledButton("texto", true, {})
}

@Composable
fun KronosFilledButton(
    text: String,
    enabled: Boolean,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
){
    Button(
        onClick = { onClick?.invoke() },
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = ButtonColors.background,
            contentColor = ButtonColors.text
        )
    ) {
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}