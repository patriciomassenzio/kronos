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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun preview(){
    val context = LocalContext.current
    RegisterView(Modifier.fillMaxSize(), NavController(context), RegisterViewModel(context))
}

@Composable
fun RegisterView(
    modifier: Modifier,
    navController: NavController,
    registerViewModel: RegisterViewModel
) {
    val user: String by registerViewModel.user.observeAsState(initial = "")
    val email: String by registerViewModel.email.observeAsState(initial = "")
    val pin: String by registerViewModel.pin.observeAsState(initial = "")
    val password: String by registerViewModel.password.observeAsState(initial = "")
    val passwordRepeat by registerViewModel.passwordRepeat.observeAsState("")
    val isLoginEnable: Boolean by registerViewModel.isLoginEnable.observeAsState(initial = false)
    val emailError by registerViewModel.emailError.observeAsState(null)
    val passwordError by registerViewModel.passwordError.observeAsState(null)

    val validUser by registerViewModel.validEmail.observeAsState(true)
    val validEmail by registerViewModel.validEmail.observeAsState(true)
    val validPin by registerViewModel.validEmail.observeAsState(true)
    val validPassword by registerViewModel.validEmail.observeAsState(true)
    val validRepeatPassword by registerViewModel.validEmail.observeAsState(true)

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
            Text(text = "Registro de cuenta", color = AppColors.title, fontSize = 32.sp, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.weight(1f))


            KronosInput(
                title = "Usuario",
                value = user,
                keyboardType = KeyboardType.Text,
                isValid = validUser,
                onValueChange = {
                    registerViewModel.onKronosInputChanged(
                        inputTitle = "Usuario",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Correo electronico o numero de telefono",
                value = email,
                keyboardType = KeyboardType.Email,
                isValid = validEmail,
                onValueChange = {
                    registerViewModel.onKronosInputChanged(
                        inputTitle = "Correo electronico o numero de telefono",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Pin",
                value = pin,
                keyboardType = KeyboardType.NumberPassword,
                isValid = validEmail,
                onValueChange = {
                    registerViewModel.onKronosInputChanged(
                        inputTitle = "Pin",
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
                    registerViewModel.onKronosInputChanged(
                        inputTitle = "Contraseña",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Repetir Contraseña",
                value = passwordRepeat,
                keyboardType = KeyboardType.Password,
                isValid = validPassword,
                onValueChange = {
                    registerViewModel.onKronosInputChanged(
                        inputTitle = "Repetir Contraseña",
                        value = it
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp));

            KronosFilledButton(
                text = "Registrarse",
                enabled = isLoginEnable,
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
            )

            if (registerViewModel.showAlert) {
                LoginAlerts(
                    title = "Alerta",
                    message = "Usuario y/o contrasena Incorrectos",
                    confirmText = "Aceptar",
                    onConfirmClick = { registerViewModel.closeAlert() },
                    onDismissClick = { /*TODO*/ }
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