package com.kronos.presentation.ui.components.mesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kronos.R
import com.kronos.model.Mesa
import com.kronos.presentation.ui.views.home.HomeView
import com.kronos.presentation.utils.Extensions.toIntOffset

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun preview(){
    val context = LocalContext.current
    HomeView(rememberNavController(), Modifier.fillMaxSize())
}

@Composable
/**
 * 0 - abajo
 * 1 - arriba
 * 2 - derecha
 * 3 - izquierda
 */

fun MesaBarraView(
    mesa: Mesa,
    zoom: Float = 1f,
    onDrag: ((Mesa, Offset) -> Unit)? = null,
    modifier: Modifier = Modifier,
    lado: Int,
    onClick: (Mesa) -> Unit
){
    val isVertical = lado > 1//es izquierda o derecha
    val width = if(isVertical) 20.dp else 40.dp
    val height = if(isVertical) 40.dp else 20.dp


    val rotacion = if(isVertical){//es izquierda o derecha
        if (lado == 3){//izquierda
            90f
        }
        else{//derecha
            -90f
        }
    }
    else {//es arriba o abajo
        if (lado == 1){//arriba
            180f
        }
        else{//abajo
            0f
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset {(mesa.posicion.value * zoom).toIntOffset()}
            .scale(zoom)
    ) {

        Box(
            modifier = Modifier
                .size(width = width, height = height)
                .background(color = mesa.color.value, shape = RoundedCornerShape(100.dp))
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        //mesa.posicion.value += pan * (.5f * zoom)
                    }
                }
                .clickable { onClick.invoke(mesa) }
        )

        Image(
            painter = painterResource(R.drawable.ic_siilla_barra),
            contentDescription = "",
            modifier = Modifier.width(40.dp).graphicsLayer(rotationZ = rotacion).offset{Offset(0f, 25f).toIntOffset()}
        )
        val circleSize = if(width > height) height else width
        Box(
            contentAlignment = Alignment.Center, // Cambiado a Center para centrar en ambas direcciones
            modifier = Modifier
                .size(circleSize)
                .background(color = Color.Transparent, shape = RoundedCornerShape(40.dp))
                .border(0.1.dp, color = Color(0xFF1C171A), shape = RoundedCornerShape(40.dp))
        ) {
            Text(
                text = mesa.id.toString(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Black,
                // No es necesario fillMaxSize() para el texto, puede causar problemas de alineación
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}
