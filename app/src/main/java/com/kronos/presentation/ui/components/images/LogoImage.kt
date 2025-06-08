package com.kronos.presentation.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.kronos.R

@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.formularieimage),
        contentDescription = "formularieimage", contentScale = ContentScale.Crop,
        modifier = modifier
    )
}