package com.kronos.presentation.ui.views.home.pedidos.pedidodetalle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kronos.enumeration.TipoMesa
import com.kronos.model.Asiento
import com.kronos.model.Camarero
import com.kronos.model.Mesa
import com.kronos.model.MesaRedonda
import com.kronos.model.Producto
import com.kronos.presentation.ui.components.asientocard.AsientoCard
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.theme.dark00
import com.kronos.presentation.ui.theme.dark50
import com.kronos.presentation.ui.theme.dark90
import com.kronos.presentation.ui.views.home.listadoMesasFija
import com.kronos.presentation.ui.views.home.pedidos.camareros

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun PedidosViewPreview(){
    val mesa = (listadoMesasFija[11] as MesaRedonda).copy(
        asientos = listOf(
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
    )

    PedidosDetalleView(mesa, navController = NavController(LocalContext.current))
}

@Composable
fun PedidosDetalleView(mesa: Mesa, navController: NavController) {
    val total = mesa.asientos.flatMap { asiento ->
        asiento.productos
    }.sumOf { producto ->
        producto.precio
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dark90)
            .padding(20.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            KronosFilledButton("Volver", true, { navController.popBackStack() })
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Mesa ${mesa.id}",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = dark00
            )
            Spacer(modifier = Modifier.weight(1f))
            KronosFilledButton("Volver", true, { navController.popBackStack() })
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(0.dp, 10.dp).weight(1f),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(mesa.asientos){
                AsientoCard(asiento = it)
            }
        }

        Row(

            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .width(170.dp)
//                .height(60.dp)
                .border(width = 1.dp, color = dark00, shape = RoundedCornerShape(10.dp))
                .background(color = dark90)
                .padding(10.dp)
        ) {
            Text(
                text = "Total: ",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = dark50

            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = "€ ${total}",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = dark00

            )

        }
    }
}