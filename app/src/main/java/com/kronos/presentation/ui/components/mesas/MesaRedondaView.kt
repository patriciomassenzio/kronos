package com.kronos.presentation.ui.components.mesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.model.Mesa
import com.kronos.presentation.utils.Extensions.toPx
import kotlin.math.roundToInt

@Composable
fun MesaRedondaView(
    sillas: Int,
    mesa: Mesa,
    zoom: Float = 1f,
    onDrag: ((Mesa, Offset) -> Unit)? = null,
    modifier: Modifier = Modifier,
    onClick: (Mesa) -> Unit
) {
    val imagenSilla = painterResource(id = R.drawable.ic_silla_curva)
    val tamanoMesa = (sillas * 9).dp
    val distanciaSillas = (tamanoMesa / 2 + 4.dp).toPx()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(8.dp)
            .offset { IntOffset(mesa.posicion.value.x.roundToInt(), mesa.posicion.value.y.roundToInt()) }
            .scale(zoom)
    ) {
        // Sillas
        for (i in 0 until sillas) {
            // Calcular posición y rotación para cada silla
            val angle = (360f / sillas) * i
            val radians = Math.toRadians(angle.toDouble())
            val xOffset = (distanciaSillas * Math.cos(radians)).toFloat()
            val yOffset = (distanciaSillas * Math.sin(radians)).toFloat()

            // Dibujar la silla
            Box(
                modifier = Modifier
                    .offset { IntOffset(xOffset.toInt(), yOffset.toInt()) }
                    .width(35.dp)
                    .rotate(angle + 90) // Rotar la silla para alinearla correctamente
            ) {
                Image(
                    painter = imagenSilla,
                    contentDescription = "Chair",
                    modifier = Modifier.fillMaxSize().padding(2.dp)
                )
            }
        }

        // Mesa (Círculo)
        Box(
            modifier = Modifier
                .size(tamanoMesa)
                .clip(CircleShape)
                .border(1.dp, Color.Transparent, CircleShape)
                .background(color = mesa.color.value)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
//                        mesa.posicion.value += pan * (.5f * zoom)
                    }
                }
                .clickable { onClick.invoke(mesa) }
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mesa ${mesa.id}",
                modifier = Modifier,
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "00:00:00",
                modifier = Modifier,
                color = Color.Red,
                fontSize = 9.sp
            )
        }


    }
}