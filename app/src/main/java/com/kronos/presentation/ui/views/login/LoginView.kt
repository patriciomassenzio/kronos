package com.kronos.presentation.ui.views.login

import android.widget.Toast
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.kronos.data.datastore.StoreToken
import com.kronos.empleados.EmpleadoStorage
import com.kronos.navigation.Routes
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.components.buttons.KronosOutlinedButton
import com.kronos.presentation.ui.components.buttons.LinkButton
import com.kronos.presentation.ui.components.images.LinkSocialImage
import com.kronos.presentation.ui.components.images.LogoImage
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.theme.AppColors
import com.kronos.presentation.ui.theme.background
import com.kronos.presentation.ui.views.alerts.LoginAlerts

import kotlinx.coroutines.launch

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun Preview(){
    LocalContext.current
    
}

@Composable
fun LoginView(
    modifier: Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val validPassword by loginViewModel.validPassword.observeAsState(true)
    val validEmail by loginViewModel.validEmail.observeAsState(true)
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    val pinEmpleado: String by loginViewModel.pin.observeAsState(initial = "")
    val validPin by loginViewModel.validpin.observeAsState(true)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreToken(context)

    Row(
        modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = background)
                .padding(50.dp, 0.dp),
            verticalArrangement = Arrangement.Center
               ,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Inicio de sesion", color = AppColors.title, fontSize = 32.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.weight(1f))


            KronosInput(
                title = "Correo electronico o numero de telefono",
                value = email,
                keyboardType = KeyboardType.Email,
                isValid = validEmail,
                onValueChange = {
                    loginViewModel.onKronosInputChanged(
                        inputTitle = "Correo electronico o numero de telefono",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Contraseña",
                value = password,
                keyboardType = KeyboardType.Password,
                isValid = validPassword,
                onValueChange = {
                    loginViewModel.onKronosInputChanged(
                        inputTitle = "Contraseña",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Pin de Usuario",
                value = pinEmpleado,
                keyboardType = KeyboardType.NumberPassword,
                isValid = validPin,
                onValueChange = {
                    loginViewModel.onKronosInputChanged(
                        inputTitle = "Pin de Usuario",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.size(16.dp));

            KronosFilledButton(
                text = "Ingresar",
                enabled = isLoginEnable,
                onClick = {
                    loginViewModel.login(
                        username = email,
                        password = password,
                        onResult = { scope.launch { dataStore.saveToken(it.toString()) } },
                        onSuccess = { navController.navigate(Routes.Home.route) }
                    )


                    navController.navigate(Routes.Home.route)
                },
                modifier = Modifier.fillMaxWidth(),
            )
            KronosFilledButton(
                text = "Ingresar con PIN",
                enabled = pinEmpleado.length == 4,
                onClick = {
                    val empleado = EmpleadoStorage.buscarEmpleadoPorPin(context, pinEmpleado)
                    if (empleado != null) {
                        Toast.makeText(
                            context,
                            "Bienvenido, ${'$'}{empleado.nombre}",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Routes.Home.route)
                    } else {
                        Toast.makeText(context, "PIN inválido", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            if (loginViewModel.showAlert) {
                LoginAlerts(
                    title = "Alerta",
                    message = "Usuario y/o contrasena Incorrectos",
                    confirmText = "Aceptar",
                    onConfirmClick = { loginViewModel.closeAlert() },
                    onDismissClick = { /*TODO*/ }
                )
            }

            Spacer(modifier = Modifier.size(4.dp))

            LinkButton(text = "¿Ya tenes una cuenta?", onClick = { })
            KronosOutlinedButton(
                text = "Registrarse",
                onClick = { navController.navigate(Routes.Register.route) },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider(Modifier.padding(top = 73.dp, bottom = 20.dp))
            LinkSocialImage()
            LinkButton(text = "¿Necesitas ayuda para iniciar sesion?", onClick = { })
        }
        LogoImage(modifier = Modifier.weight(1f).fillMaxHeight())
    }
}