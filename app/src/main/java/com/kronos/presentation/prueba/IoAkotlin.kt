package com.kronos.presentation.prueba

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

//@Composable
//fun KeychainExampleScreen() {
//    val context = LocalContext.current
//    val keychainVM: KeychainViewModel = viewModel { KeychainViewModel(context) }
//
//    // Estados para UI
//    var tokenInput by remember { mutableStateOf("") }
//    var message by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Text(
//            text = "Keychain Manager",
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        // Campo para token
//        OutlinedTextField(
//            value = tokenInput,
//            onValueChange = { tokenInput = it },
//            label = { Text("Token de prueba") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Botones de acciones
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            Button(
//                onClick = {
//                    keychainVM.saveToKeychain("authToken", tokenInput)
//                    message = "Token guardado"
//                },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text("Guardar Token")
//            }
//
//            Button(
//                onClick = {
//                    val token = keychainVM.getTokenFromKeychain("authToken")
//                    message = if (token != null) {
//                        "Token: $token"
//                    } else {
//                        "No hay token guardado"
//                    }
//                },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text("Obtener Token")
//            }
//        }
//
//        // Botón para petición autenticada
//        Button(
//            onClick = {
//                keychainVM.makeAuthenticatedRequest(
//                    onSuccess = { response ->
//                        message = "✅ Petición exitosa: ${response.take(100)}..."
//                    },
//                    onError = { error ->
//                        message = "❌ Error: $error"
//                    }
//                )
//            },
//            enabled = !keychainVM.isLoading
//        ) {
//            if (keychainVM.isLoading) {
//                CircularProgressIndicator(
//                    modifier = Modifier.size(16.dp),
//                    strokeWidth = 2.dp
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//            }
//            Text("Hacer Petición Autenticada")
//        }
//
//        // Botón de logout
//        Button(
//            onClick = {
//                keychainVM.logoutUserLeader()
//                message = "Logout realizado"
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.error
//            )
//        ) {
//            Text("Logout Completo")
//        }
//
//        // Indicadores de estado
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            colors = CardDefaults.cardColors(
//                containerColor = if (keychainVM.isTokenValid)
//                    MaterialTheme.colorScheme.primaryContainer
//                else
//                    MaterialTheme.colorScheme.errorContainer
//            )
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text("Estado del Token: ${if (keychainVM.isTokenValid) "✅ Válido" else "❌ Inválido"}")
//                Text("Cargando: ${if (keychainVM.isLoading) "Sí" else "No"}")
//                Text("Tiene Token: ${if (keychainVM.hasValidToken()) "Sí" else "No"}")
//            }
//        }
//
//        // Mensaje de resultado
//        if (message.isNotEmpty()) {
//            Card(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = message,
//                    modifier = Modifier.padding(16.dp),
//                    style = MaterialTheme.typography.bodyMedium
//                )
//            }
//        }
//    }
//}