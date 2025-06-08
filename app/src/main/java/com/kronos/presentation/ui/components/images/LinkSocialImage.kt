package com.kronos.presentation.ui.components.images

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kronos.R
import com.kronos.presentation.ui.components.buttons.KronosIconButton


@Preview
@Composable
fun preview(){
    LinkSocialImage()
}

@Composable
fun LinkSocialImage() {
    Row(horizontalArrangement = Arrangement.spacedBy(80.dp)) {
        KronosIconButton(text = "Google", image = R.drawable.google)
        KronosIconButton(text = "Apple ID", image = R.drawable.apple)
        KronosIconButton(text = "Microsoft", image = R.drawable.microsoft)
    }
}
