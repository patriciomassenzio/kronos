package com.kronos.presentation.ui.components.appBars

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kronos.R
import com.kronos.navigation.Routes

@Composable
fun BottomBar(navController: NavController){

    NavigationBar(containerColor = Color(0xFF1C171A)) {
        NavigationBarItem(selected = false, onClick = { navController.navigate(Routes.Salon.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "",
                    Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
            },
            alwaysShowLabel = true,
            label = { Text(text = "Salón", color = MaterialTheme.colorScheme.primary) }

        )
        NavigationBarItem(selected = false, onClick = { navController.navigate(Routes.Pedidos.route) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.PointOfSale,
                    contentDescription = "",
                    Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
            },
            alwaysShowLabel = true,
            label = { Text(text = "Pedidos", color = MaterialTheme.colorScheme.primary) }

        )
        NavigationBarItem(selected = false, onClick = { navController.navigate(Routes.Reservas.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "",
                    Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
            },
            alwaysShowLabel = true,
            label = { Text(text = "Reservas", color = MaterialTheme.colorScheme.primary) }

        )
        NavigationBarItem(selected = false, onClick = { navController.navigate(Routes.Inventario.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "",
                    Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
            },
            alwaysShowLabel = true,
            label = { Text(text = "Invetario", color = MaterialTheme.colorScheme.primary) }

        )


    }


}