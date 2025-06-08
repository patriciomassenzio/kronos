package com.kronos.presentation.ui.components.mesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kronos.R
import com.kronos.model.Mesa
import com.kronos.presentation.utils.Extensions.toIntOffset


@Composable
fun MesaPosView(
    mesa: Mesa,
    modifier: Modifier,
    zoom: Float = 1f
){

    Box(
        modifier = modifier
            .offset {(mesa.posicion.value * zoom).toIntOffset()}
            .scale(zoom)
    ){
        Image(
            painter = painterResource(R.drawable.terminal_pos_1),
            contentDescription = "",
            modifier = Modifier.width(100.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
//                        mesa.posicion.value += pan * (.5f * zoom)
                    }
               },
            contentScale = ContentScale.Crop
        )

    }

}