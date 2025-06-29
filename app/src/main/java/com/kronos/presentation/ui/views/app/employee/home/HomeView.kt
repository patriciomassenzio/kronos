package com.kronos.presentation.ui.views.app.employee.home


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kronos.enumeration.TipoMesa
import com.kronos.model.Asiento
import com.kronos.model.MesaBarra
import com.kronos.model.MesaCuadrada
import com.kronos.model.MesaOvalada
import com.kronos.model.MesaPos
import com.kronos.model.MesaRedonda
import com.kronos.model.Producto
import com.kronos.navigation.EmployeesNavigation
import com.kronos.presentation.ui.components.appBars.BottomBar
import com.kronos.presentation.ui.components.appBars.TopBar

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun preview(){
//    HomeView(rememberNavController(), Modifier.fillMaxSize())
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun HomeView(appNavController: NavHostController, modifier: Modifier, context: Context) {
    val homeNavController = rememberNavController()

    Scaffold(
        modifier = modifier,
        topBar = { TopBar(appNavController, homeNavController) },
        bottomBar = { BottomBar(homeNavController) },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
                EmployeesNavigation(homeNavController)
            }
        }
    )
}


val asientos = listOf(
    Asiento(
        numero = 1,
        enablePollo = true,
        enableTrigo = true,
        nombreCliente = "Miguel Pucheta",
        productos = listOf(
            Producto("Bisteca a la fiorentina", 20.35)
        )
    ),
    Asiento(
        numero = 2,
        enablePollo = false,
        enableTrigo = true,
        nombreCliente = "Sofía Mendoza",
        productos = listOf(
            Producto("Risotto de setas", 15.80),
            Producto("Tiramisú", 7.50)
        )
    ),
    Asiento(
        numero = 3,
        enablePollo = true,
        enableTrigo = false,
        nombreCliente = "Javier Quiroga",
        productos = listOf(
            Producto("Ensalada César sin crutones", 12.45),
            Producto("Salmón a la parrilla", 22.90),
            Producto("Copa de vino tinto", 8.75)
        )
    ),
    Asiento(
        numero = 4,
        enablePollo = false,
        enableTrigo = false,
        nombreCliente = "Laura Fernández",
        productos = listOf(
            Producto("Ensalada vegetariana", 11.25),
            Producto("Sorbet de limón", 6.50)
        )
    ),
    Asiento(
        numero = 5,
        enablePollo = true,
        enableTrigo = true,
        nombreCliente = "Martín Gutiérrez",
        productos = listOf(
            Producto("Pasta carbonara", 14.95),
            Producto("Chuleta de cerdo", 18.70),
            Producto("Agua mineral", 2.50)
        )
    ),
    Asiento(
        numero = 6,
        enablePollo = true,
        enableTrigo = true,
        nombreCliente = "Carolina Torres",
        productos = listOf(
            Producto("Pizza margherita", 13.40),
            Producto("Gelato de chocolate", 5.75)
        )
    )
)

val listadoMesasFija = listOf(
    MesaPos(id=0, tipoMesa=TipoMesa.MesaPos, posicion=mutableStateOf(value=Offset(213.1f, 317.0f))),
    MesaBarra(id=4, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(636.8f, 83.2f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaBarra(id=1, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(624.3f, 4.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=3),
    MesaBarra(id=2, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(624.2f, 33.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=3),
    MesaBarra(id=3, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(623.9f, 59.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=3),
    MesaBarra(id=5, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(664.3f, 82.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaBarra(id=6, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(692.5f, 82.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaBarra(id=7, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(721.5f, 83.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaBarra(id=8, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(749.5f, 83.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaBarra(id=9, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(775.9f, 84.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaBarra(id=10, tipoMesa=TipoMesa.MesaBarra, posicion=mutableStateOf(value=Offset(801.2f, 84.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), lado=0),
    MesaRedonda(id=11, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(698.7f, -6.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
    MesaCuadrada(id=12, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(771.7f, 312.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaCuadrada(id=13, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(820.5f, 312.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaCuadrada(id=14, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(719.4f, 315.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaCuadrada(id=15, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(674.6f, 314.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaRedonda(id=16, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(750.5f, -6.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
    MesaRedonda(id=17, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(652.9f, -7.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
    MesaRedonda(id=18, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(754.7f, 41.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
    MesaRedonda(id=19, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(705.5f, 43.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
    MesaRedonda(id=20, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(653.9f, 40.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=4),
    MesaCuadrada(asientos = asientos, id=21, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(166.6f, 18.2f)), color=mutableStateOf(value=Color(0xFF8FCB9B)), alto=2, ancho=2),
    MesaCuadrada(asientos = asientos, id=22, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(168.0f, 84.2f)), color=mutableStateOf(value=Color(0xFF8FCB9B)), alto=2, ancho=2),
    MesaCuadrada(asientos = asientos, id=23, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(242.1f, 17.3f)), color=mutableStateOf(value=Color(0xFF988FCC)), alto=2, ancho=2),
    MesaCuadrada(asientos = asientos, id=24, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(313.9f, 15.9f)), color=mutableStateOf(value=Color(0xFFF7A072)), alto=2, ancho=2),
    MesaCuadrada(asientos = asientos, id=25, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(242.6f, 80.6f)), color=mutableStateOf(value=Color(0xFFF7A072)), alto=2, ancho=2),
    MesaCuadrada(asientos = asientos, id=26, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(316.9f, 79.7f)), color=mutableStateOf(value=Color(0xFF988FCC)), alto=2, ancho=2),
    MesaRedonda(id=27, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(145.0f, 6.1f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=8),
    MesaCuadrada(id=28, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(243.4f, 153.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=2, ancho=3),
    MesaRedonda(id=29, tipoMesa=TipoMesa.MesaRedonda, posicion=mutableStateOf(value=Offset(321.5f, 8.5f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), sillas=8),
    MesaOvalada(id=30, tipoMesa=TipoMesa.MesaOvalada, posicion=mutableStateOf(value=Offset(149.9f, 215.2f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=2, ancho=1),
    MesaCuadrada(id=31, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(237.6f, 234.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=2, ancho=3),
    MesaOvalada(id=32, tipoMesa=TipoMesa.MesaOvalada, posicion=mutableStateOf(value=Offset(315.9f, 216.0f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=2, ancho=1),
    MesaCuadrada(id=33, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(673.3f, 265.6f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaCuadrada(id=34, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(719.5f, 267.8f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaCuadrada(id=35, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(773.9f, 268.3f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1),
    MesaCuadrada(id=36, tipoMesa=TipoMesa.MesaCuadrada, posicion=mutableStateOf(value=Offset(820.8f, 266.7f)), color=mutableStateOf(value=Color(0xFFB0B0B0)), alto=1, ancho=1)

)






val mesaFija = (listadoMesasFija[11] as MesaRedonda).copy(
    asientos = asientos
)










