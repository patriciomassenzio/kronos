package com.kronos.presentation.ui.components.mesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.model.Mesa
import com.kronos.presentation.utils.Extensions.toIntOffset

@Composable
fun MesaCuadradaView(
    alto: Int,
    ancho: Int,
    mesa: Mesa,
    zoom: Float = 1f,
    onDrag: ((Mesa, Offset) -> Unit)? = null,
    modifier: Modifier = Modifier,
    onClick: (Mesa) -> Unit
){
    val asientos = 2
    val tamano = if(ancho == 1 && alto == 1) 32f else 25f

    //menor tamaño en la imagen de la silla (ya sea ancho o alto)
    val cornerModifier = if(ancho == 1 && alto == 1) Modifier.size(12.dp) else Modifier.size(8.dp)
    val squareModifier = Modifier.size(tamano.dp)
    val verticalModifier = Modifier.height(tamano.dp).padding(0.5.dp)
    val horizontalModifier = Modifier.width(tamano.dp).padding(0.5.dp)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset {(mesa.posicion.value * zoom).toIntOffset()}
            .scale(zoom)
    ) {
        Column(
            modifier = Modifier
        ) {
            for (row in 1..alto + asientos) {
                Row()
                {
                    for (column in 1..ancho + asientos) {
                        val isCorner = ((row == 1 || row == alto + asientos) &&
                                (column == 1 || column == ancho + asientos))
//                        val isCorner = ((row == 1 && column == 1) ||
//                                (row == alto + asientos && column == ancho + asientos) ||
//                                (row == 1 && column == ancho + asientos) ||
//                                (row == alto + asientos && column == 1))

                        val isTop = (row == 1)
                        val isBottom = (row == alto + asientos)
                        val isLeft = (column == 1)
                        val isRight = (column == ancho + asientos)

                        if (isCorner) { Spacer( modifier = cornerModifier) }
                        else if (isTop) {
                            Image(
                                painter = painterResource(R.drawable.ic_silla_top),
                                contentDescription = null,
                                modifier = horizontalModifier,
                                alignment = Alignment.Center
                            )
                        }
                        else if (isBottom) {
                            Image(
                                painter = painterResource(R.drawable.ic_silla_bottom),
                                contentDescription = null,
                                modifier = horizontalModifier,
                                alignment = Alignment.Center
                            )
                        }
                        else if (isLeft) {
                            Image(
                                painter = painterResource(R.drawable.ic_silla_left),
                                contentDescription = null,
                                modifier = verticalModifier,
                                alignment = Alignment.Center
                            )
                        }
                        else if (isRight) {
                            Image(
                                painter = painterResource(R.drawable.ic_silla_right),
                                contentDescription = null,
                                modifier = verticalModifier,
                                alignment = Alignment.Center
                            )
                        }
                        else {
                            Box(
                                modifier = squareModifier
                                    .background(color = mesa.color.value)
                                    .align(alignment = Alignment.CenterVertically)
                                    .pointerInput(Unit) {
                                        detectTransformGestures { _, pan, _, _ ->
                                            //fix para que no se vaya la mesa
                                            val newOffset = mesa.posicion.value + (pan * .5f)
//                                            mesa.posicion.value = newOffset
                                        }
                                    }
                                    .clickable { onClick(mesa) }

                            )
                        }
                    }
                }
            }
        }
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
                fontSize = 10.sp
            )
        }
    }
}