package com.kronos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kronos.data.datastore.StorePin
import com.kronos.data.datastore.StoreToken
import com.kronos.presentation.ui.views.login.LoginViewModel

import com.kronos.presentation.ui.views.home.HomeView
import com.kronos.presentation.ui.views.home.inventario.InventarioView
import com.kronos.presentation.ui.views.home.listadoMesasFija
import com.kronos.presentation.ui.views.home.mesaFija
import com.kronos.presentation.ui.views.home.pedidos.pedidodetalle.PedidosDetalleView
import com.kronos.presentation.ui.views.home.pedidos.PedidosView
import com.kronos.presentation.ui.views.home.reservas.ReservaView
import com.kronos.presentation.ui.views.home.salon.SalonView
import com.kronos.presentation.ui.views.login.LoginView
import com.kronos.presentation.ui.views.register.RegisterView
import com.kronos.presentation.ui.views.register.RegisterViewModel

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    )
    {//"RegisterPadView"
        composable(Routes.Login.route) {
            LoginView(modifier, navController, LoginViewModel(context))
        }
        composable(Routes.Home.route) {
            HomeView(navController, modifier)
        }
        composable(Routes.Register.route) {
            RegisterView(
                modifier,
                navController,
                RegisterViewModel(context)
            )
        }
//        composable(Routes.SingUpPin.route) {
//                SingUpPinView(navController, EmployeesLoginViewModel(context))
//
//        }
//        composable(Routes.RegisterPad.route) {
//            RegisterPadView(
//                Modifier,
//                LoginViewModel(context),
//                RegisterPinViewModel(context),
//                PinViewModel(),
//                navController,
//                userToken.value
//            )
//
//        }
    }
}

@Composable
fun HomeNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.Salon.route
    )
    {
        composable(Routes.Salon.route) {
            SalonView(listadoMesasFija)
        }
        composable(Routes.Pedidos.route) {
            PedidosView(navController)
        }
        composable(Routes.ConfiguracionPedidos.route) {
            PedidosDetalleView(mesaFija, navController)
        }
        composable(Routes.Inventario.route) {
            InventarioView(navController)
        }
        composable(Routes.Reservas.route) {
            ReservaView(navController)
        }
    }
}