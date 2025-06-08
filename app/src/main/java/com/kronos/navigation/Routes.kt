package com.kronos.navigation

sealed class Routes(val route: String){
    data object Login: Routes("login")
    data object Home: Routes("home")
    data object Register: Routes("regitro")
    data object Salon: Routes("salon")
    data object Pedidos: Routes("pedidos")
    data object Reservas: Routes("reservas")
    data object Inventario: Routes("inventario")
    data object ConfiguracionPedidos: Routes("configuracion")
//    data object SingUpPin: Routes("signup")
//    data object RegisterPad: Routes("registerpad")

}