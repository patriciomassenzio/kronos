package com.kronos.presentation.ui.views.app.employee.pad

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kronos.R
import com.kronos.presentation.ui.components.buttons.SignUpButton
import com.kronos.presentation.ui.components.buttons.LinkButton
import com.kronos.presentation.ui.components.images.LogoImage
import com.kronos.presentation.ui.viewmodel.EmployeesLoginViewModel
import com.kronos.navigation.Routes
import com.kronos.presentation.ui.components.alerts.LoginAlerts
import com.kronos.presentation.ui.theme.AppColors
import com.kronos.presentation.ui.theme.ButtonColors
import com.kronos.presentation.ui.theme.InputColors
import com.kronos.presentation.ui.theme.dark90
import com.kronos.presentation.ui.views.app.employee.home.pedidos.PedidosView

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun SingInPinViewPreview(){
    SingInPinView(
        navController = NavController(LocalContext.current),
        employeesLoginViewModel = EmployeesLoginViewModel(LocalContext.current)

    )

}



@Composable
fun SingInPinView(
    navController: NavController,
    employeesLoginViewModel: EmployeesLoginViewModel,
) {

    Column(
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = dark90)
            ) {
                Column(
                    Modifier
                        //.fillMaxWidth()
                        .fillMaxSize()
                        .padding(56.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Title(text = "Inicio de sesion con PIN")
                    //Spacer(modifier = Modifier.size(90.dp))
                    //Text(text = "Introduzca su PIN")
                    //Spacer(modifier = Modifier.size(10.dp))
                    NumericPad(
                        employeesLoginViewModel,
                        navController
                    )
                    //Spacer(modifier = Modifier.size(10.dp))

                    Spacer(modifier = Modifier.size(40.dp))
//                    SignUpButton("Iniciar sesión") {
//
//                    }
                    HorizontalDivider(Modifier.padding(top = 23.dp))
                    LinkButton(
                        text = "¿Necesitas ayuda para iniciar sesión?",
                        onClick = { TODO() }
                        ,
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                LogoImage()
            }
        }
    }
}
//@Preview
//@Composable
//
//fun NumericPad(){}
@Composable
fun NumericPad(employeesLoginViewModel: EmployeesLoginViewModel, navController: NavController) {
    var pines by remember {  mutableStateOf("") }  // Almacena el valor del PIN como Int
    var errorMessage by remember { mutableStateOf("") }  // Muestra el error si el PIN es incorrecto
    val navigationEvent by employeesLoginViewModel.navigationEvent.observeAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier .background(color = dark90)
    ) {

        // Vista del PIN
        Row(
            modifier = Modifier.padding(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (index in 0 until 4) {
                Surface(
                    modifier = Modifier
                        .size(54.dp)
                        .padding(10.dp),
                    shape = CircleShape,
                    color = if (pines?.toString()?.length ?: 0 > index)
                        ButtonColors.background else AppColors.background,
                    border = BorderStroke(2.dp, Color.Gray)
                ) {}
            }
        }

        // Muestra un mensaje de error si el PIN es incorrecto
        if (errorMessage.isNotEmpty()) {
            println("ERROR")
            println(pines)
//            Text(text = errorMessage, color = Color.Red)
        }

        // Pad numérico
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (row in listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(7, 8, 9),
                listOf(null, 0, null) // La última fila tiene un 0 en el medio
            )) {
                Row {
                    row.forEach { digit ->
                        if (digit != null) {
                            NumberPab(image = painterResource(id = getDrawableForDigit(digit))) {
                                //pines = updatePinNumber(pines, digit)
                                // Aseguramos que pines no sea null. Si es null, comenzamos con 0.
                                pines = updatePinNumber(pines, digit).toInt().toString()
                            }
                        } else {
                            Spacer(modifier = Modifier.width(68.dp)) // Espacio para los valores nulos
                        }
                    }
                }
            }
        }

        LinkButton(
            text = "¿Ya tenes una cuenta?",
            onClick = { navController.popBackStack() }
        )
        SignUpButton("Iniciar sesión") {
            val pin = pines

            if (pines.length == 4) {
                println("PIN ingresado: $pines")
                employeesLoginViewModel.employeesPin(pines) // Pasa el string directamente
                pines = "" // Limpiar después de enviar
            } else {
                errorMessage = "PIN debe tener 4 dígitos"
                pines = ""
            }

            if ( pin.length == 4) {
                // Acción si el PIN es correcto
                //errorMessage = ""  // Reinicia el mensaje de error
                println("PIN correcto")
                employeesLoginViewModel.employeesPin(pines)
            } else {
                // Acción si el PIN es incorrecto
                println(pines)
                errorMessage = "PIN incorrecto"
                pines = ""  // Restablece el valor del PIN
            }




        }


        LaunchedEffect(navigationEvent) {
            if (navigationEvent == true) {
                navController.navigate( Routes.Home.route)
            }
        }

        if (employeesLoginViewModel.showAlert) {
            LoginAlerts(
                title = "",
                message = "PIN Incorrecto",
                confirmText = "Aceptar",
                onConfirmClick = { employeesLoginViewModel.closeAlert() },
                onDismissClick = { /*TODO*/ }
            )
            pines =  ""
        }
    }
}

// Función para obtener la imagen según el dígito
fun getDrawableForDigit(digit: Int): Int {
    return when (digit) {
        0 -> R.drawable.numb_0
        1 -> R.drawable.numb_1
        2 -> R.drawable.numb_2
        3 -> R.drawable.numb_3
        4 -> R.drawable.numb_4
        5 -> R.drawable.numb_5
        6 -> R.drawable.numb_6
        7 -> R.drawable.numb_7
        8 -> R.drawable.numb_8
        9 -> R.drawable.numb_9
        else -> R.drawable.ic_launcher_foreground  // Default
    }
}






@Composable
fun NumberPab(image: Painter, function: () -> Unit) {
    Row(Modifier.padding(horizontal = 34.dp, vertical = 16.dp)) {
        IconButton(
            onClick = { function() },
            modifier = Modifier.size(52.dp),

            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFF82284E),
                contentColor = Color.White // Color neutral10 Falta paleta de colores neutral
            ),
        ) {
            Icon(
                painter = image,
                contentDescription = "Number",
                modifier = Modifier.size(30.dp)
            )
        }
    }


}



// Función para manejar los números del pad
fun updatePinNumber(currentPin: String, newDigit: Int): String {
    val currentPinString = currentPin.toString()
    return if (currentPinString.length < 4) {
        (currentPinString + newDigit)  // Añadimos el nuevo dígito al PIN
    } else {
        currentPin  // Mantén el valor actual si ya tiene 4 dígitos
    }

}

// Función de validación del PIN
//fun validatePin(pin: Int, ): Boolean {
//    return pin ==
//}




