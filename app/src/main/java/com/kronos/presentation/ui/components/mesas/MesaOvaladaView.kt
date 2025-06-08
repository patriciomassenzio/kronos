package com.kronos.presentation.ui.components.mesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.enumeration.TipoMesa
import com.kronos.model.Mesa
import com.kronos.model.MesaOvalada
import com.kronos.presentation.ui.theme.mesaNaranja
import com.kronos.presentation.ui.views.home.salon.SalonView
import kotlin.math.roundToInt

@Preview(widthDp = 800, heightDp = 400)
@Composable
fun mesaOvaladaPreview(){

    var listaMesas by remember { mutableStateOf<List<Mesa>>(listOf(
        MesaOvalada(
            id = System.currentTimeMillis().toInt(),
            tipoMesa = TipoMesa.MesaOvalada,
            posicion = mutableStateOf(Offset(50F, 50F)),
            color = mutableStateOf(mesaNaranja),
            ancho = 1,
            alto = 2
        ),
        MesaOvalada(
            id = System.currentTimeMillis().toInt(),
            tipoMesa = TipoMesa.MesaOvalada,
            posicion = mutableStateOf(Offset(200f, 50f)),
            color = mutableStateOf(mesaNaranja),
            ancho = 2,
            alto = 1
        ),
        MesaOvalada(
            id = System.currentTimeMillis().toInt(),
            tipoMesa = TipoMesa.MesaOvalada,
            posicion = mutableStateOf(Offset(380f, 50F)),
            color = mutableStateOf(mesaNaranja),
            ancho = 1,
            alto = 1
        ),
        MesaOvalada(
            id = System.currentTimeMillis().toInt(),
            tipoMesa = TipoMesa.MesaOvalada,
            posicion = mutableStateOf(Offset(520f, 50f)),
            color = mutableStateOf(mesaNaranja),
            ancho = 2,
            alto = 2
        )
    )) }

    SalonView(listaMesas)
}

@Composable
fun MesaOvaladaView(
    alto: Int,
    ancho: Int,
    mesa: Mesa,
    zoom: Float = 1f,
    onDrag: ((Mesa, Offset) -> Unit)? = null,
    modifier: Modifier = Modifier,
    onClick:  (Mesa) -> Unit
){
    val asientos = 4
    val medioAsientos = asientos / 2
    val tamano = 27f
    val tamanoMedio = tamano / 2


    val asientosAncho = if(ancho == 1) 1 else 2
    val asientosAlto = if(alto == 1) 1 else 2
    //menor tamaño en la imagen de la silla (ya sea ancho o alto)
    val cornerModifier = Modifier.size(8.dp)//ok
    val medioVerticalModifier = Modifier
        .height(tamanoMedio.dp)
        .width(8.dp)//ok
    val medioHorizontalModifier = Modifier
        .width(tamanoMedio.dp)
        .height(8.dp)//ok
    val squareModifier = Modifier.size(tamano.dp)
    val tableCornerModifier = Modifier.size(tamanoMedio.dp)
    val tableHorizontalModifier = Modifier
        .height(tamanoMedio.dp)
        .width(tamano.dp)
    val tableVerticalModifier = Modifier
        .width(tamanoMedio.dp)
        .height(tamano.dp)
    val verticalModifier = Modifier
        .height(tamano.dp)
        .width(8.dp)
        .padding(0.5.dp)
    val horizontalModifier = Modifier
        .width(tamano.dp)
        .height(8.dp)
        .padding(0.5.dp)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset {
                IntOffset(
                    mesa.posicion.value.x.roundToInt(),
                    mesa.posicion.value.y.roundToInt()
                )
            }
            .scale(zoom)
    ) {
        //sillas
        Column(modifier = Modifier) {
            for (row in 1..alto + asientos) {
                Row()
                {
                    for (column in 1..ancho + asientos) {
                        if (row == 1 || row == alto+asientos) {
                            if(column == 1 || column == ancho + asientos){
                                Box(modifier = cornerModifier)
                            }
                            else if(column == 2 || column == ancho + 3){
                                Box(modifier = medioHorizontalModifier)
                            }
                            else if(row == 1){
                                Image(
                                    painter = painterResource(R.drawable.ic_silla_top),
                                    contentDescription = null,
                                    modifier = horizontalModifier
                                )
                            }
                            else{
                                Image(
                                    painter = painterResource(R.drawable.ic_silla_bottom),
                                    contentDescription = null,
                                    modifier = horizontalModifier
                                )
                            }
                        }
                        else if (column == 1 || column == ancho+asientos) {
                            if(row == 2 || row == alto + 3){
                                Box(modifier = medioVerticalModifier)
                            }
                            else if(column == 1){
                                Image(
                                    painter = painterResource(R.drawable.ic_silla_left),
                                    contentDescription = null,
                                    modifier = verticalModifier
                                )
                            }
                            else{
                                Image(
                                    painter = painterResource(R.drawable.ic_silla_right),
                                    contentDescription = null,
                                    modifier = verticalModifier
                                )
                            }
                        }
                        else{//es mesa
                            if(row == 2 || column == 2 || row == alto + medioAsientos + 1|| column == ancho + medioAsientos + 1){
                                if((row == 2 || row == alto + medioAsientos + 1) && (column == 2 || column == ancho + medioAsientos + 1)){

                                    if(ancho == 1){
                                        Box(modifier = tableHorizontalModifier)
                                    }
                                    else{
                                        Box(modifier = tableCornerModifier)
                                    }
                                }
                                else if(row == 2 || row == alto + medioAsientos + 1){
                                    if(ancho != 1){
                                        Box(modifier = tableHorizontalModifier)
                                    }
                                }
                                else{
                                    Box(modifier = tableVerticalModifier)
                                }
                            }
                            else
                            {
                                Box(modifier = squareModifier)
                            }
                        }
                    }
                }
            }
        }
        //mesa
        Column(modifier = Modifier
            .padding(tamanoMedio.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    //fix para que no se vaya la mesa
                    val newOffset = mesa.posicion.value + (pan * .5f)
//                    mesa.posicion.value = newOffset
                }
            }
            .clickable { onClick.invoke(mesa) }) {
            for (row in 1..alto + 1) {
                Row()
                {
                    for (column in 1..ancho + 1) {
                        val isTopLeft = (row == 1 && column == 1)
                        val isTopRight = (row == 1 && column == ancho + 1)
                        val isBottomLeft = (row == alto + 1 && column == 1)
                        val isBottomRight = (row == alto + 1 && column == ancho + 1)
                        if (isTopLeft) {
                            Box(
                                modifier = squareModifier
                                    .background(
                                        color = mesa.color.value,
                                        shape = RoundedCornerShape(topStart = 30.dp)
                                    )
                                    .pointerInput(Unit) {
                                        detectTransformGestures { _, pan, _, _ ->
                                            //fix para que no se vaya la mesa
                                            val newOffset = mesa.posicion.value + (pan * .5f)
//                                            mesa.posicion.value = newOffset
                                        }
                                    }
                            )
                        }
                        else if (isBottomLeft) {
                            Box(
                                modifier = squareModifier
                                    .background(
                                        color = mesa.color.value,
                                        shape = RoundedCornerShape(bottomStart = 30.dp)
                                    )
                                    .pointerInput(Unit) {
                                        detectTransformGestures { _, pan, _, _ ->
                                            //fix para que no se vaya la mesa
                                            val newOffset = mesa.posicion.value + (pan * .5f)
//                                            mesa.posicion.value = newOffset
                                        }
                                    }
                            )

                        }
                        else if (isTopRight) {
                            Box(
                                modifier = squareModifier
                                    .background(
                                        color = mesa.color.value,
                                        shape = RoundedCornerShape(topEnd = 30.dp)
                                    )
                            )
                        }
                        else if (isBottomRight) {
                            Box(
                                modifier = squareModifier
                                    .background(
                                        color = mesa.color.value,
                                        shape = RoundedCornerShape(bottomEnd = 30.dp)
                                    )
                                    .pointerInput(Unit) {
                                        detectTransformGestures { _, pan, _, _ ->
                                            //fix para que no se vaya la mesa
                                            val newOffset = mesa.posicion.value + (pan * .5f)
//                                            mesa.posicion.value = newOffset
                                        }
                                    }
                            )

                        }
                        else{
                            Box(modifier = squareModifier.background(color = mesa.color.value))
                        }
                    }
                }
            }
        }
        //texto
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