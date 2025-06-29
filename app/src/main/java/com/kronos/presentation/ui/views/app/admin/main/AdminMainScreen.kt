package com.kronos.presentation.ui.views.app.admin.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kronos.navigation.AdminNavigation
import com.kronos.navigation.Routes
import com.kronos.presentation.ui.components.appBars.BottomBar
import com.kronos.presentation.ui.components.appBars.TopBar
import com.kronos.presentation.ui.theme.dark80

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun AdminHomeView(appNavController: NavHostController, modifier: Modifier) {
    val adminNavController = rememberNavController()
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(appNavController, adminNavController) },
        bottomBar = { BottomBar(adminNavController) },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
                AdminNavigation(adminNavController)
            }
        }
    )
}

@Preview
@Composable
fun AdminMainScreen(){
    AdminMainScreen(NavHostController(LocalContext.current))
}

@Composable
fun AdminMainScreen(nav: NavHostController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color = dark80)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { nav.navigate(Routes.Restaurantes.route) }) { Text("Administrar Restaurantes") }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { nav.navigate(Routes.Roles.route) }) { Text("Administrar Roles") }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { nav.navigate(Routes.RegisterEmployee.route) }) { Text("Registrar Empleados") }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { nav.navigate(Routes.SingInPin.route) }) { Text("SinginPin (PIN)") }

    }
}