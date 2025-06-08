package com.kronos.presentation.ui.components.appBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(appBarController: NavController, homeNavcontroller: NavController) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1C171A),
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.surface,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = "Salon",
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle
            )
        },
        actions = {

            TextButton(onClick = {
                    appBarController.popBackStack()
            }) {
                Text(text = "Cerrar sesion")
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "",
                )
            }

        }

    )
}