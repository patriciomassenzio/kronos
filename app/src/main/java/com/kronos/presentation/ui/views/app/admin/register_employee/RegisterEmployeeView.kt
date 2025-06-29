package com.kronos.presentation.ui.views.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kronos.enumeration.Inputs
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.components.buttons.KronosOutlinedButton
import com.kronos.presentation.ui.components.buttons.LinkButton
import com.kronos.presentation.ui.components.images.LinkSocialImage
import com.kronos.presentation.ui.components.images.LogoImage
import com.kronos.presentation.ui.components.inputs.KronosDropdown
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.theme.AppColors
import com.kronos.presentation.ui.theme.background
import com.kronos.presentation.ui.components.alerts.LoginAlerts

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun previewRegisterEmployeeView(){
    val context = LocalContext.current
    val navController = NavController(context)
    RegisterEmployeeView(Modifier.fillMaxSize(), navController, RegisterEmployeeViewModel(context, navController))
}

@Composable
fun RegisterEmployeeView(
    modifier: Modifier,
    navController: NavController,
    viewModel: RegisterEmployeeViewModel
) {
    val name: String by viewModel.name.collectAsState()
    val email: String by viewModel.email.collectAsState()
    val pin: String by viewModel.pin.collectAsState()
    val rol: String by viewModel.rol.collectAsState()//camarero, cocinero, bachero, bartender, runner, seguridad, cajero
    val restaurante by viewModel.restaurante.collectAsState()//las cañitas, valencia, tinogasta, laferrere, santo domingo, la romana

    val isRegisterEnabled by viewModel.isRegisterEnabled.collectAsState()
    val validName by viewModel.validName.collectAsState()
    val validEmail by viewModel.validEmail.collectAsState()
    val validPin by viewModel.validPin.collectAsState()
    val validRol by viewModel.validRol.collectAsState()
    val validRestaurante by viewModel.validRestaurante.collectAsState()

    Row(
        modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = background)
                .padding(50.dp, 0.dp),) {

            item {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(color = background)
                        .padding(50.dp, 0.dp),
                    verticalArrangement = Arrangement.Center
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Registro de Cuenta",
                        color = AppColors.title,
                        fontSize = 32.sp, modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    KronosInput(
                        title = "Usuario",
                        value = name,
                        keyboardType = KeyboardType.Text,
                        isValid = validName,
                        onValueChange = { viewModel.onInputChange(input = Inputs.Nombre, value = it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    KronosInput(
                        title = "Email",
                        value = email,
                        keyboardType = KeyboardType.Email,
                        isValid = validEmail,
                        onValueChange = { viewModel.onInputChange(input = Inputs.Email, value = it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    KronosInput(
                        title = "Pin",
                        value = pin,
                        keyboardType = KeyboardType.NumberPassword,
                        isValid = validPin,
                        onValueChange = { viewModel.onInputChange(input = Inputs.Pin, value = it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    //camarero, cocinero, bachero, bartender, runner, seguridad, cajero
                    KronosDropdown(
                        title = "Rol",
                        options = listOf("camarero", "cocinero", "bachero", "bartender", "runner", "seguridad", "cajero"),
                        selectedOption = rol,
                        isValid = validRol,
                        onOptionSelected = { viewModel.onInputChange(input = Inputs.Rol, value = it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    //las cañitas, valencia, tinogasta, laferrere, santo domingo, la romana
                    KronosDropdown(
                        title = "Restaurante",
                        options = listOf("las cañitas", "valencia", "tinogasta", "laferrere", "santo domingo", "la romana"),
                        selectedOption = restaurante,
                        isValid = validRestaurante,
                        onOptionSelected = { viewModel.onInputChange(input = Inputs.Restaurante, value = it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.size(16.dp));

                    KronosFilledButton(
                        onClick = {  viewModel.registerEmployee(navController)},
                        enabled = isRegisterEnabled,
                        text = "Registrar",
                        modifier = Modifier.fillMaxWidth(),
                    )

                    if (viewModel.showAlert) {
                        LoginAlerts(
                            title = "Alerta",
                            message = viewModel.alertMessage,
                            confirmText = "Aceptar",
                            onConfirmClick = { viewModel.closeAlert() },
                            onDismissClick = { /* opcional */ }
                        )

                    }

                    Spacer(modifier = Modifier.size(4.dp))

                    LinkButton(text = "¿Ya tenes una cuenta?", onClick = { navController.popBackStack() })
                    KronosOutlinedButton(
                        text = "Iniciar Sesion",
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    HorizontalDivider(Modifier.padding(top = 73.dp, bottom = 20.dp))
                    LinkSocialImage()
                    LinkButton(text = "¿Necesitas ayuda para iniciar sesion?", onClick = { navController.popBackStack() })
                }
            }
        }
        LogoImage(modifier = Modifier.weight(1f).fillMaxHeight())
    }
}