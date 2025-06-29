package com.kronos.presentation.ui.views.app.employee.home.salon

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.kronos.enumeration.TipoMesa
import com.kronos.model.Mesa
import com.kronos.model.MesaBarra
import com.kronos.model.MesaCuadrada
import com.kronos.model.MesaOvalada
import com.kronos.model.MesaPos
import com.kronos.model.MesaRedonda
import com.kronos.presentation.ui.components.mesas.MesaBarraView
import com.kronos.presentation.ui.components.mesas.MesaCuadradaView
import com.kronos.presentation.ui.components.mesas.MesaOvaladaView
import com.kronos.presentation.ui.components.mesas.MesaPosView
import com.kronos.presentation.ui.components.mesas.MesaRedondaView
import com.kronos.presentation.ui.theme.dark90
import com.kronos.presentation.ui.views.app.employee.home.listadoMesasFija
import com.kronos.presentation.utils.Extensions.drawGrid

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun preview(){
    SalonView(listadoMesasFija)
}

@Composable
fun SalonView(mesas: List<Mesa> = emptyList()){
    var mesaSeleccionada by remember { mutableStateOf<Mesa?>(null) }
    var dialogType by remember { mutableStateOf(TipoMesa.None) }
    var offset by remember { mutableStateOf(Offset(-260f, 0f)) }
    var zoom by remember { mutableFloatStateOf(0.9f) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = dark90)
        .pointerInput(Unit) {
            detectTransformGestures { _, pan, zoomChange, _ ->
//            offset = Offset(offset.x + pan.x, offset.y + pan.y)
//            Log.d("zoomChange", zoomChange.toString())
//            zoom *= zoomChange
//            zoom = zoom.coerceIn(0.5f, 5f)
            }
        }) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawGrid(size = size, offset = offset, zoom = zoom)
        }

        mesas.forEach { mesa ->
            Log.d("MESA", mesa.toString())
            val posicionConZoom = Offset(
                x = (mesa.posicion.value.x * zoom) + offset.x,
                y = (mesa.posicion.value.y * zoom) + offset.y
            )
            when (mesa) {
                is MesaBarra -> {
                    MesaBarraView(
                        mesa = mesa,
                        zoom = zoom,
                        modifier = Modifier.offset { IntOffset(posicionConZoom.x.toInt(), posicionConZoom.y.toInt()) },
                        lado = mesa.lado,
                        onClick = { mesaSeleccionada = mesa }


                    )
                }
                is MesaCuadrada -> {
                    MesaCuadradaView(
                        alto = mesa.alto,
                        ancho = mesa.ancho,
                        mesa = mesa,
                        zoom = zoom,
                        modifier = Modifier.offset { IntOffset(posicionConZoom.x.toInt(), posicionConZoom.y.toInt()) },
                        onClick = { mesaSeleccionada = mesa }
                    )
                }
                is MesaOvalada -> {
                    MesaOvaladaView(
                        alto = mesa.alto,
                        ancho = mesa.ancho,
                        mesa = mesa,
                        zoom = zoom,
                        modifier = Modifier.offset { IntOffset(posicionConZoom.x.toInt(), posicionConZoom.y.toInt()) },
                        onClick = { mesaSeleccionada = mesa }
                    )
                }
                is MesaRedonda -> {
                    MesaRedondaView(
                        sillas = mesa.sillas,
                        mesa = mesa,
                        zoom = zoom,
                        modifier = Modifier.offset { IntOffset(posicionConZoom.x.toInt(), posicionConZoom.y.toInt()) },
                        onClick = { mesaSeleccionada = mesa }
                    )
                }
                is MesaPos -> {
                    MesaPosView(
                        mesa = mesa,
                        zoom = zoom,
                        modifier = Modifier.offset { IntOffset(posicionConZoom.x.toInt(), posicionConZoom.y.toInt()) },
                    )
                }
            }
        }

        if (mesaSeleccionada != null) {
            com.kronos.presentation.ui.components.menuLateral.MenuLateral(
                mesa = mesaSeleccionada!!,
                modifier = Modifier.align(Alignment.CenterEnd),
                onClose = { mesaSeleccionada = null })
        }
    }
}