package com.kronos.presentation.ui.views.registerpad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.components.buttons.KronosOutlinedButton
import com.kronos.presentation.ui.components.buttons.LinkButton
import com.kronos.presentation.ui.components.images.LinkSocialImage
import com.kronos.presentation.ui.components.images.LogoImage
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.views.login.LoginViewModel


import com.kronos.presentation.ui.viewmodel.PinViewModel
import com.kronos.presentation.ui.viewmodel.RegisterPinViewModel

@Composable
fun RegisterPadView(
    modifier: Modifier,
    //navController: NavController,
    loginViewModel: LoginViewModel,
    registerPinViewModel: RegisterPinViewModel,
    pinViewModel: PinViewModel,
    navController: NavController,
    userToken: String?
) {


    Column(modifier = Modifier.fillMaxHeight()) {
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(start = 48.dp, end = 69.5.dp)

            ) {
                Column(
                    modifier = modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
//                    Header(
//                        text = "Registro de cuenta",
//                        modifier = Modifier
//                            .padding(top = 28.dp, bottom = 28.dp)
//                            .fillMaxWidth()
//                    )
                    //Spacer(modifier = Modifier.padding(top = 28.dp))
//                    BodyRegisterPad(
//                        loginViewModel,
//                        registerPinViewModel,
//                        pinViewModel,
//                        navController
//                    )

                    HorizontalDivider(
                        Modifier.padding(
                            top = 0.dp, bottom = 20.dp
                        )
                    ) // Esperar a adri que le de el margen correspondiente

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        LinkSocialImage()
                    }
                    Spacer(modifier = Modifier.padding(top = 16.75.dp))
                    LinkButton(
                        text = "¿Necesitas ayuda para iniciar sesion?",
                        onClick = { TODO() },
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                //.background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                LogoImage()
            }
        }
    }
}

@Composable
fun BodyRegisterPad(
    viewModel: RegisterPadViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController
) {

    val user: String by loginViewModel.user.observeAsState(initial = "")
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val pin: String by loginViewModel.pin.observeAsState("")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val repeatPass: String by loginViewModel.passwordRepeat.observeAsState("")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = true)
    val intValue by viewModel.intValue.observeAsState()
//    val navigationEvent by registerPinViewModel.navigationEvent.observeAsState()

//    val password: String by loginViewModel.password.observeAsState(initial = "")
//    val passwordRepeat by loginViewModel.passwordRepeat.observeAsState("")
//    val passwordError by loginViewModel.passwordError.observeAsState(null)

    val validEmail by loginViewModel.validEmail.observeAsState(true)
    val validrepeatPassword by loginViewModel.validrepeatPassword.observeAsState(true)
    val  validuser by loginViewModel.validuser.observeAsState(true)
    val  validpin by loginViewModel.validuser.observeAsState(true)
    val  validpassword by loginViewModel.validuser.observeAsState(true)

    Column() {
//        UserInput(user, "Usuario", type = KeyboardType.Text) {
//            loginViewModel.isValidUsername(user = it)
//        }

        KronosInput(
            title = "Usuario",
            value = user,
            keyboardType = KeyboardType.Text,
            isValid = validuser,
            onValueChange = {
                loginViewModel.isValidUsername(user = it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(12.dp))

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

        Spacer(modifier = Modifier.size(12.dp))

        KronosInput(
            title = "Pin de Usuario",
            value = pin,
            keyboardType = KeyboardType.NumberPassword,
            isValid = validpin,
            onValueChange = {
                loginViewModel.onKronosInputChanged(
                    inputTitle = "Pin de Usuario",
                    value = it
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(12.dp))

        KronosInput(
            title = "Contraseña",
            value = password ,
            keyboardType = KeyboardType.Password,
            isValid = validpassword,
            onValueChange = {
                loginViewModel.onKronosInputChanged(
                    inputTitle = "Contraseña",
                    value = it
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(12.dp))

        KronosInput(
            title = "Repetir Contraseña",
            value = repeatPass,
            keyboardType = KeyboardType.Password,
            isValid = validrepeatPassword,
            onValueChange = {
                loginViewModel.onKronosInputChanged(
                    inputTitle = "Repetir Contraseña",
                    value = it
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(20.dp))

        KronosFilledButton(
            text = "Registrarse",
            enabled = isLoginEnable,
//            onClick = {
//                registerPinViewModel.usersPin(user, email, pin.toInt())
//            },
            modifier = Modifier.fillMaxWidth()
        )

//
//        LoginButton(
//            onClick = {
//                intValue?.let {
//                    registerPinViewModel.usersPin(
//                        user, email, it
////                        ,onResult = { pin ->
////                            scope.launch {
////                                if (pin != null) {
////                                    dataStore.savePin(pin)
////                                }
////                            }
////                        },
//                    )
//                }
//                //numberValue?.let { registerPinViewModel.usersPin(user, email, it) }
//            },
//            text = "Registrarse",
//            modifier = Modifier.fillMaxWidth(),
//            //email = email,
//            // password = password,
//            isLoginEnable,
//        )

//        LaunchedEffect(navigationEvent) {
//            if (navigationEvent == true) {
//                navController.navigate( Routes.SingUpPin.route)
//            }
//        }
//
//        if (registerPinViewModel.showAlert) {
//            LoginAlerts(
//                title = "Alerta",
//                message = "Usuario y/o PIN Incorrectos",
//                confirmText = "Aceptar",
//                onConfirmClick = { registerPinViewModel.closeAlert() },
//                onDismissClick = { }
//            )
//        }

//        LoginButton(
//            { TODO() },
//            text = "Registrarse",
//            modifier = Modifier
//
//                .fillMaxWidth(),
//            email = email,
//            password = pass,
//            user = user,
//            isPasswordValid = true,
//
//            )

        Spacer(modifier = Modifier.size(20.dp))

        KronosOutlinedButton(
            text = "Iniciar Sesión",
            onClick = { },
            modifier = Modifier.fillMaxWidth().align(Alignment.Start)

        )
    }
}

@Composable
fun PinNumeric(textHolder: String, value: String, onValueChange: (Any?) -> Unit) {


    OutlinedTextField(
        value = value,
        label = { Text(textHolder, color = MaterialTheme.colorScheme.onPrimary) },
        onValueChange = { newValue -> onValueChange(newValue) },
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
        ),
        //placeholder = { Text("Ingresar 4 digitos") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )


}

