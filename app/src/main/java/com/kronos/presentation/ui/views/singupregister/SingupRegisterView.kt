package com.kronos.presentation.ui.views.singupregister

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kronos.presentation.ui.components.buttons.LinkButton
import com.kronos.presentation.ui.components.images.LinkSocialImage
import com.kronos.presentation.ui.components.images.LogoImage
import com.kronos.presentation.ui.views.login.LoginViewModel
import com.kronos.presentation.ui.views.register.RegisterViewModel

@Composable
fun SingupRegister(
    modifier: Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel

){
    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(start = 48.dp, end = 69.5.dp)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
//                    Header(
//                        text = "Registro", modifier = Modifier
//                            .padding(top = 28.dp, bottom = 51.dp)
//                            .fillMaxWidth()
//                    )
//                    Spacer(modifier = Modifier.padding(top = 32.dp))
//                    BodyRegister(navController, loginViewModel, registerViewModel)
//                    BodySingupRegister()
                    HorizontalDivider(Modifier.padding(top = 27.dp, bottom = 20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 23.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        LinkSocialImage()
                    }
                    LinkButton(
                        text = "¿Necesitas ayuda para iniciar sesión?",
                        onClick = { TODO() }
                        ,Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                LogoImage()
            }
        }

    }


}
//
//@Composable
//fun BodySingupRegister() {
//    Column {
//        KronosInput(
//            title = "Usuario",
//            value = user,
//            keyboardType = KeyboardType.Text,
//            isValid = validuser,
//            onValueChange = {
//                loginViewModel.isValidUsername(user = it)
//            },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//    }
//
//
//}
