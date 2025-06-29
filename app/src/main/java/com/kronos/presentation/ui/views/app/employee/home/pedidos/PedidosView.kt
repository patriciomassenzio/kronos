package com.kronos.presentation.ui.views.app.employee.home.pedidos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kronos.enumeration.TipoMesa
import com.kronos.model.Camarero
import com.kronos.model.EstadoMesa
import com.kronos.model.MesaBarra
import com.kronos.model.MesaCuadrada
import com.kronos.model.MesaRedonda
import com.kronos.presentation.ui.components.appBars.CamareroBar
import com.kronos.presentation.ui.theme.dark90

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun PedidosViewPreview(){
    PedidosView(navController = NavController(LocalContext.current))
}

@Composable
fun PedidosView(navController: NavController) {
    val listaCamareros by remember { mutableStateOf<List<Camarero>>(camareros) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dark90)
            .padding(120.dp, 30.dp),
    ) {
        items(listaCamareros){
            CamareroBar(camarero = it, navController)
        }
    }
}

val camareros = listOf(
    Camarero(
        nombre = "María López",
        mesas = listOf(
            MesaBarra(estado = EstadoMesa.OCUPADO, id=10, tipoMesa= TipoMesa.MesaBarra, posicion= mutableStateOf(value= Offset(801.2f, 84.1f)), color= mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaRedonda(id=11, tipoMesa= TipoMesa.MesaRedonda, posicion= mutableStateOf(value= Offset(698.7f, -6.0f)), color= mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
            MesaCuadrada(id=12, tipoMesa= TipoMesa.MesaCuadrada, posicion= mutableStateOf(value= Offset(771.7f, 312.7f)), color= mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(id=13, tipoMesa= TipoMesa.MesaCuadrada, posicion= mutableStateOf(value= Offset(820.5f, 312.5f)), color= mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(estado = EstadoMesa.BLOQUEADO, id=14, tipoMesa= TipoMesa.MesaCuadrada, posicion= mutableStateOf(value= Offset(719.4f, 315.0f)), color= mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Carlos Rodríguez",
        mesas = listOf(
            MesaBarra(id=9, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(775.9f, 84.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaBarra(estado = EstadoMesa.OCUPADO, id=10, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(801.2f, 84.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaRedonda(estado = EstadoMesa.OCUPADO, id=11, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(698.7f, -6.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
            MesaCuadrada(id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(estado = EstadoMesa.BLOQUEADO, id=13, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(820.5f, 312.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(id=14, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(719.4f, 315.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Ana Martínez",
        mesas = listOf(
            MesaBarra(id=9, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(775.9f, 84.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaBarra(id=10, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(801.2f, 84.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaRedonda(id=11, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(698.7f, -6.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
            MesaCuadrada(id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Juan Gómez",
        mesas = listOf(
            MesaCuadrada(estado = EstadoMesa.OCUPADO, id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Laura Fernández",
        mesas = listOf(
            MesaBarra(estado = EstadoMesa.OCUPADO, id=9, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(775.9f, 84.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaBarra(id=10, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(801.2f, 84.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaRedonda(id=11, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(698.7f, -6.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
            MesaCuadrada(id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(estado = EstadoMesa.OCUPADO, id=13, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(820.5f, 312.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(id=14, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(719.4f, 315.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Miguel Torres",
        mesas = listOf(
            MesaRedonda(estado = EstadoMesa.OCUPADO, id=11, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(698.7f, -6.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
            MesaCuadrada(estado = EstadoMesa.OCUPADO, id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(estado = EstadoMesa.OCUPADO, id=13, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(820.5f, 312.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(estado = EstadoMesa.OCUPADO, id=14, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(719.4f, 315.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Sofía Hernández",
        mesas = listOf(
            MesaBarra(estado = EstadoMesa.BLOQUEADO, id=9, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(775.9f, 84.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaBarra(id=10, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(801.2f, 84.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaRedonda(id=11, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(698.7f, -6.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
            MesaCuadrada(id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    ),
    Camarero(
        nombre = "Pablo Sánchez",
        mesas = listOf(
            MesaBarra(estado = EstadoMesa.BLOQUEADO, id=9, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(775.9f, 84.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaBarra(estado = EstadoMesa.BLOQUEADO, id=10, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(801.2f, 84.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
            MesaCuadrada(id=13, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(820.5f, 312.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
            MesaCuadrada(id=14, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(719.4f, 315.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),

            )
    )
)