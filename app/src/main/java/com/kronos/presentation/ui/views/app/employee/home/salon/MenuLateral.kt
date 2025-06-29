package com.kronos.presentation.ui.views.app.employee.home.salon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.MoveDown
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kronos.presentation.ui.components.buttons.LoginButton
import com.kronos.presentation.ui.components.inputs.UserInput
import com.kronos.presentation.ui.viewmodel.EstadoMesaViewModel


@Composable
fun MenuLateral(expanded: Boolean, numeroMesa: Int, onDismissRequest: () -> Unit) {
    var agregarMesaVisible by remember { mutableStateOf(false) }

    val clientes = listOf(
        "Carlos Duran",
        "Laura Salcedo",
        "Marcos Salcedo",
        "Patricia Duran",
    )


    DropdownMenu(
        modifier = Modifier
            .width(400.dp)
            //.height(140.dp)
            //.size(width = 400.dp, height = 740.dp),
            .fillMaxHeight(),

        //.fillMaxWidth() ,

        //containerColor = MaterialTheme.colorScheme.primary,
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                //.size(width = 400.dp, height = 740.dp)
                //.fillMaxSize()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            //verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 300.dp, height = 100.dp)

                //.wrapContentSize(Alignment.TopCenter)

            ) {


                Box(Modifier.wrapContentSize(Alignment.Center)) {

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",

                            )
                    }

                    Text(
                        "Mesa $numeroMesa",
                        Modifier.padding(start = 52.dp, top = 16.dp)
                    )



                    IconButton(
                        onClick = {},
                        Modifier.padding(start = 360.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                        )
                    }
                }
                Box() {
                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = "",
                        Modifier
                            .padding(top = 60.dp, start = 52.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        "6",
                        Modifier
                            .padding(top = 60.dp, start = 84.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.Timer,
                        contentDescription = "",
                        Modifier
                            .padding(top = 60.dp, start = 196.dp)
                            .size(20.dp),
                        tint = MaterialTheme.colorScheme.tertiary,
                    )
                    Text(
                        "00:51:26",
                        Modifier
                            .padding(top = 60.dp, start = 228.dp)
                    )


                }

                HorizontalDivider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )

            }
            Column(modifier = Modifier.fillMaxHeight()) {
                clientes.forEach { clientes ->
                    Text(
                        modifier = Modifier.padding(72.dp, 15.5.dp, 56.dp, 15.5.dp),
                        text = clientes,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,

                        )
                    HorizontalDivider(color = MaterialTheme.colorScheme.surface)


                }

            }

            Box() {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "",
                    Modifier
                        .padding(top = 15.dp, start = 360.dp)
                        .size(24.dp)
                        .clickable {
                            agregarMesaVisible = true
                        },
                    tint = MaterialTheme.colorScheme.tertiary,
                )
                Text(
                    "Agregar mesa",
                    Modifier
                        .padding(72.dp, 15.5.dp, 56.dp, 15.5.dp)
                )
                // HorizontalDivider(color = MaterialTheme.colorScheme.surface)

            }
            Box() {
                Icon(
                    imageVector = Icons.Filled.MoveDown,
                    contentDescription = "",
                    Modifier
                        .padding(top = 15.dp, start = 360.dp)
                        .size(24.dp)
                        .clickable {},
                    tint = MaterialTheme.colorScheme.tertiary,
                )
                Text(
                    "Asignar mesa",
                    Modifier
                        .padding(72.dp, 15.5.dp, 56.dp, 15.5.dp)
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.surface)
            }


            Spacer(Modifier.padding(80.dp))
            Box(
                Modifier
                    .height(100.dp)
                    .fillMaxSize()
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                HorizontalDivider()
                LoginButton(
                    {}, "Modificar",
                    modifier = Modifier.padding(start = 24.dp, top = 16.dp),
                    //.size(width = 30.dp, height = 10.dp),
                    true,
                )
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.padding(start = 140.dp, top = 16.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Desocupar",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }

            }

            if (agregarMesaVisible) {
                AsignarMesa(seleccionMesa = numeroMesa, onDismiss = { agregarMesaVisible = false })

            }


        }


    }

}


@Composable
fun AsignarMesa(modifier: Modifier = Modifier, seleccionMesa: Int, onDismiss: () -> Unit, mesaViewModel: EstadoMesaViewModel = viewModel()) {

    val colorDeMesa by mesaViewModel.estadoMesa

    val openDialog = remember { mutableStateOf(true) }
    val cantidadPersona = remember { mutableStateOf("") }
    var cantidadConfirmada by remember { mutableStateOf("") }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },


            title = {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = "",
                        Modifier
                            .padding(top = 24.dp, bottom = 16.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                    Text(text = "Confirmar asignacion")
                }


            },
            text = {
                Column() {
                    Text(
                        "Mesa $seleccionMesa", fontSize = 16.sp,
                    )
                    UserInput(
                        cantidadPersona.value,
                        "Cantidad de personas",
                        type = KeyboardType.Number,
                        onValueChange = {
                            input ->
                            if (input.all { it.isDigit() }) {
                                cantidadPersona.value = input
                            }

                        }
                    )
                }

            },

            confirmButton = {

                LoginButton(
                    {
                        mesaViewModel.alternarEstado(cantidadPersona.value)
                        //cantidadConfirmada = cantidadPersona.value
                    }, "Aceptar",
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                    //.size(width = 30.dp, height = 10.dp),
                    true,
                )
            },
            dismissButton = {

                HorizontalDivider(color = MaterialTheme.colorScheme.surface)

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.padding(start = 140.dp, top = 24.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Cancelar",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                }
            }



        )

    }
}



@Preview(device = "id:pixel_tablet")
@Composable
fun PreviewDrawer() {
    AsignarMesa(seleccionMesa = 1, onDismiss = {})
}