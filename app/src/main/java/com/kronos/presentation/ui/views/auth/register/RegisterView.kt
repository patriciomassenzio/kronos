package com.kronos.presentation.ui.views.auth.register

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
import com.kronos.enumeration.Inputs
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.components.buttons.KronosOutlinedButton
import com.kronos.presentation.ui.components.buttons.LinkButton
import com.kronos.presentation.ui.components.images.LinkSocialImage
import com.kronos.presentation.ui.components.images.LogoImage
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.theme.AppColors
import com.kronos.presentation.ui.theme.background
import com.kronos.presentation.ui.components.alerts.LoginAlerts

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
fun previewRegisterView(){
    val context = LocalContext.current
    val navController = NavController(context)
    RegisterView(Modifier.fillMaxSize(), navController, RegisterViewModel(context, navController))
}

@Composable
fun RegisterView(
    modifier: Modifier,
    navController: NavController,
    viewModel: RegisterViewModel
) {
    val user: String by viewModel.user.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val passwordRepeat by viewModel.passwordRepeat.observeAsState("")
    val isRegisterEnabled: Boolean by viewModel.isRegisterEnabled.observeAsState(initial = true)

    val validUser by viewModel.validUser.observeAsState(true)
    val validEmail by viewModel.validEmail.observeAsState(true)
    val validPassword by viewModel.validPassword.observeAsState(true)
    val validPasswordRepeat by viewModel.validRepeatPassword.observeAsState(true)

    Row(
        modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = background)
                .padding(50.dp, 0.dp)) {

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
            Text(text = "Registro ",
                color = AppColors.title,
                fontSize = 32.sp, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            KronosInput(
                title = "Usuario",
                value = user,
                keyboardType = KeyboardType.Text,
                isValid = validUser,
                onValueChange = { viewModel.onInputChange(input = Inputs.User, value = it) },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Correo electronico o numero de telefono",
                value = email,
                keyboardType = KeyboardType.Email,
                isValid = validEmail,
                onValueChange = { viewModel.onInputChange(input = Inputs.Email, value = it) },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Contraseña",
                value = password,
                keyboardType = KeyboardType.Password,
                isValid = validPassword,
                onValueChange = { viewModel.onInputChange(input = Inputs.Password, value = it) },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Repetir Contraseña",
                value = passwordRepeat,
                keyboardType = KeyboardType.Password,
                isValid = validPasswordRepeat,
                onValueChange = { viewModel.onInputChange(input = Inputs.PasswordRepeat, value = it) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp));

            KronosFilledButton(
                text = "Registrarse",
                enabled = isRegisterEnabled,
                onClick = { viewModel.register() },
                modifier = Modifier.fillMaxWidth(),
            )

            if (viewModel.showAlert) {
                LoginAlerts(
                    title = "Alerta",
                    message = "Usuario y/o contrasena Incorrectos",
                    confirmText = "Aceptar",
                    onConfirmClick = { viewModel.closeAlert() },
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