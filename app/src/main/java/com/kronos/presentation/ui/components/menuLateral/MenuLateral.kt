package com.kronos.presentation.ui.components.menuLateral

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.MoveDown
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kronos.model.Mesa
import com.kronos.presentation.ui.views.home.listadoMesasFija
import com.kronos.presentation.ui.views.home.mesaFija

@Preview
@Composable
fun preview(){
    MenuLateral(mesaFija, Modifier, {})
}

@Composable
fun MenuLateral(mesa: Mesa, modifier: Modifier = Modifier, onClose: () -> Unit) {

    Box(
        modifier = modifier
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable { onClose() }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .fillMaxHeight()
                    .background(Color(0xFF141213))
                    .padding(16.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        "Mesa ${mesa.id}",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = onClose, modifier = Modifier.align(Alignment.CenterStart)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }


                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = "",
                        Modifier
                            .padding(top = 60.dp, start = 52.dp)
                            .size(24.dp),
                        tint = Color.White // Ícono en blanco
                    )
                    Text(
                        mesa.asientos.size.toString(),
                        Modifier.padding(top = 60.dp, start = 84.dp),
                        color = Color.White // Texto en blanco
                    )
                    Icon(
                        imageVector = Icons.Filled.Timer,
                        contentDescription = "",
                        Modifier
                            .padding(top = 60.dp, start = 196.dp)
                            .size(20.dp),
                        tint = Color.White,
                    )
                    Text(
                        "00:51:26",
                        Modifier.padding(top = 60.dp, start = 228.dp),
                        color = Color.White
                    )
                }



                Spacer(modifier = Modifier.height(8.dp))

                // 🔹 Lista de clientes
                Column(modifier = Modifier.fillMaxWidth()) {
                    mesa.asientos.forEach { asiento ->
                        Text(
                            text = asiento.nombreCliente,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        HorizontalDivider(color = Color.DarkGray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Botones de acción
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AccionBoton(Icons.Filled.AddCircle, "Agregar mesa")
                    AccionBoton(Icons.Filled.MoveDown, "Asignar mesa")
                    AccionBoton(Icons.Filled.MoveDown, "Reasignar mesa")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .background(Color(0xFF141213)), // Fondo oscuro opcional
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Total: €00",
                        color = Color(0xFFFFB6C1), // Color rosado claro
                        fontSize = 32.sp, // Tamaño más grande
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(modifier = Modifier.weight(1f))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 13.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .weight(1f)
                            .height(47.dp)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF82284E),
                            contentColor = Color(0xFFBCB1B4)
                        )
                    ) {
                        Text("Modificar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }

                    OutlinedButton(
                        onClick = onClose,
                        modifier = Modifier
                            .weight(1f)
                            .height(47.dp)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF57474C), // Fondo #57474C
                            contentColor = Color(0xFFBCB1B4) // Texto #BCB1B4
                        )
                    ) {
                        Text("Desocupar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun AccionBoton(icon: ImageVector, texto: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = texto,
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF82284E), shape = CircleShape)
                .padding(8.dp)
        )
        Text(
            text = texto,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

//@Composable
//fun MenuLateral(mesa: Mesa, modifier: Modifier = Modifier, onClose: () -> Unit) {
//    var agregarMesaVisible by remember { mutableStateOf(false) }
//
//    val clientes = listOf(
//        "Carlos Duran",
//        "Laura Salcedo",
//        "Marcos Salcedo",
//        "Patricia Duran",
//    )
//
//    Column(
//        modifier = modifier
//            .width(300.dp)
//            .background(AppColors.background),
//        horizontalAlignment = Alignment.CenterHorizontally
//        //verticalArrangement = Arrangement.Bottom
//    ) {
//        KronosFilledButton(text = "Cerrar Menu", enabled = true, onClick = onClose)
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(width = 300.dp, height = 100.dp)
//
//            //.wrapContentSize(Alignment.TopCenter)
//
//        ) {
//
//
//            Box(Modifier.wrapContentSize(Alignment.Center)) {
//
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "",
//
//                        )
//                }
//
////                    Text(
////                        "Mesa $numeroMesa",
////                        Modifier.padding(start = 52.dp, top = 16.dp)
////                    )
//
//
//
//                IconButton(
//                    onClick = {},
//                    Modifier.padding(start = 360.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Close,
//                        contentDescription = "",
//                    )
//                }
//            }
//            Box() {
//                Icon(
//                    imageVector = Icons.Filled.Groups,
//                    contentDescription = "",
//                    Modifier
//                        .padding(top = 60.dp, start = 52.dp)
//                        .size(24.dp),
//                    tint = MaterialTheme.colorScheme.tertiary
//                )
//                Text(
//                    "6",
//                    Modifier
//                        .padding(top = 60.dp, start = 84.dp)
//                )
//                Icon(
//                    imageVector = Icons.Filled.Timer,
//                    contentDescription = "",
//                    Modifier
//                        .padding(top = 60.dp, start = 196.dp)
//                        .size(20.dp),
//                    tint = MaterialTheme.colorScheme.tertiary,
//                )
//                Text(
//                    "00:51:26",
//                    Modifier
//                        .padding(top = 60.dp, start = 228.dp)
//                )
//
//
//            }
//
//            HorizontalDivider(
//                color = Color.Black,
//                thickness = 1.dp,
//                modifier = Modifier.align(Alignment.BottomCenter)
//            )
//
//        }
//        Column(modifier = Modifier.fillMaxHeight()) {
//            clientes.forEach { clientes ->
//                Text(
//                    modifier = Modifier.padding(72.dp, 15.5.dp, 56.dp, 15.5.dp),
//                    text = clientes,
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.primary,
//
//                    )
//                HorizontalDivider(color = MaterialTheme.colorScheme.surface)
//
//
//            }
//
//        }
//
//        Box() {
//            Icon(
//                imageVector = Icons.Filled.AddCircle,
//                contentDescription = "",
//                Modifier
//                    .padding(top = 15.dp, start = 360.dp)
//                    .size(24.dp)
//                    .clickable {
//                        agregarMesaVisible = true
//                    },
//                tint = MaterialTheme.colorScheme.tertiary,
//            )
//            Text(
//                "Agregar mesa",
//                Modifier
//                    .padding(72.dp, 15.5.dp, 56.dp, 15.5.dp)
//            )
//            // HorizontalDivider(color = MaterialTheme.colorScheme.surface)
//
//        }
//        Box() {
//            Icon(
//                imageVector = Icons.Filled.MoveDown,
//                contentDescription = "",
//                Modifier
//                    .padding(top = 15.dp, start = 360.dp)
//                    .size(24.dp)
//                    .clickable {},
//                tint = MaterialTheme.colorScheme.tertiary,
//            )
//            Text(
//                "Asignar mesa",
//                Modifier
//                    .padding(72.dp, 15.5.dp, 56.dp, 15.5.dp)
//            )
//            HorizontalDivider(color = MaterialTheme.colorScheme.surface)
//        }
//
//
//        Spacer(Modifier.padding(80.dp))
//        Box(
//            Modifier
//                .height(100.dp)
//                .fillMaxSize()
//                .fillMaxWidth()
//                .wrapContentSize(Alignment.Center)
//        ) {
//            HorizontalDivider()
//            LoginButton(
//                {}, "Modificar",
//                modifier = Modifier.padding(start = 24.dp, top = 16.dp),
//                //.size(width = 30.dp, height = 10.dp),
//                true,
//            )
//            OutlinedButton(
//                onClick = { },
//                modifier = Modifier.padding(start = 140.dp, top = 16.dp),
//                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
//                colors = ButtonDefaults.textButtonColors(
//                    contentColor = MaterialTheme.colorScheme.onPrimary
//                )
//            ) {
//                Text(
//                    text = "Desocupar",
//                    style = MaterialTheme.typography.labelLarge,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//
//            }
//
//        }
//
//        if (agregarMesaVisible) {
////                AsignarMesa(seleccionMesa = numeroMesa, onDismiss = { agregarMesaVisible = false })
//
//        }
//
//
//    }
//}