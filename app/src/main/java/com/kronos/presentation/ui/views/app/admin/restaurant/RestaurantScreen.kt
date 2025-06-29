package com.kronos.presentation.ui.views.app.admin.restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kronos.enumeration.Inputs
import com.kronos.model.Restaurant
import com.kronos.presentation.ui.components.admin.RestauranteListItem
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.theme.dark80

@Preview
@Composable
fun RestarantScreenPreview(){
    RestarantScreen(RestaurantViewModel(LocalContext.current))
}

@Composable
fun RestarantScreen(viewModel: RestaurantViewModel){
    val context = LocalContext.current
    val name: String by viewModel.name.collectAsState()
    val phone: String by viewModel.phone.collectAsState()
    val address: String by viewModel.address.collectAsState()
    val comments: String by viewModel.comments.collectAsState()
    val validName: Boolean by viewModel.validName.collectAsState()
    val validPhone: Boolean by viewModel.validPhone.collectAsState()
    val validAddress: Boolean by viewModel.validAddress.collectAsState()
    val cargando: Boolean by viewModel.cargando.collectAsState()
    //val enableCampos: Boolean by viewModel.enableCampos.observeAsState()
    val enableGuardar: Boolean by viewModel.enableGuardar.collectAsState()
    val restaurantes: List<Restaurant> by viewModel.restaurantes.collectAsState()
//    val scrollState = rememberScrollState()







//    Column(
////        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = dark80)
//
//
//    ) {
////        Spacer(modifier = Modifier.fillMaxWidth(1f))
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(1f)
//                .padding(10.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            if(enableCampos){
//                Column(
//                    modifier = Modifier
//                        .verticalScroll(scrollState)
//                        .imePadding()
//                        .fillMaxWidth()
//                    ,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    KronosInput(
//                        title = "Nombre",
//                        value = name,
//                        keyboardType = KeyboardType.Text,
//                        isValid = validName,
//                        onValueChange = { viewModel.onInputChange(Inputs.Name, it) },
//                        modifier = Modifier.fillMaxWidth(),
//
//                    )
//                    KronosInput(
//                        title = "Teléfono",
//                        value = phone,
//                        keyboardType = KeyboardType.Phone,
//                        isValid = validPhone,
//                        onValueChange = { viewModel.onInputChange(Inputs.Phone, it) },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    KronosInput(
//                        title = "Dirección",
//                        value = address,
//                        keyboardType = KeyboardType.Text,
//                        isValid = validAddress,
//                        onValueChange = { viewModel.onInputChange(Inputs.Address, it) },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    KronosInput(
//                        title = "Comentarios",
//                        value = comments,
//                        keyboardType = KeyboardType.Text,
//                        isValid = true,
//                        onValueChange = { viewModel.onInputChange(Inputs.Comments, it) },
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
//
//                        OutlinedButton(onClick = { viewModel.onCancelar() }) {
//                            Text("Cancelar")
//                        }
//
//                        Button(
//                            onClick = { viewModel.onGuardar() },
//                            enabled = enableGuardar,
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color(0XFF82284E),
//                                disabledContentColor = Color(0xFFC393A7)
//
//                            )
//                        ) {
//                            Text(if (cargando) "Guardando... " else "Guadar Restaurant")
//                        }
//                    }
//                }
//            }
//
//
//            Button(
//                onClick = {viewModel.onNuevoRestaurant()},
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0XFF82284E),
//                    disabledContentColor = Color(0xFFC393A7)
//
//                )
//            ) {
//                Text("Nuevo Restaurante")
//            }
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                items(restaurantes) { restaurant ->
//                    RestauranteListItem(
//                        restaurant = restaurant,
//                        onEditClick = { viewModel.onEditClick(restaurant) },
//                        onDeleteClick = { viewModel.onDeleteClick(restaurant) },
//                        onSelectClick = { viewModel.onSelectClick(restaurant) }
//                    )
//                }
//            }
//        }
//        Spacer(modifier = Modifier.fillMaxWidth(1f))
//    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dark80)
            .imePadding() // CAMBIO 2: imePadding aquí en el contenedor principal
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // CAMBIO 3: Convertir todo a items de LazyColumn
        //if(enableCampos) {
            item {
                KronosInput(
                    title = "Nombre",
                    value = name,
                    keyboardType = KeyboardType.Text,
                    isValid = validName,
                    onValueChange = { viewModel.onInputChange(Inputs.Name, it) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            item {
                KronosInput(
                    title = "Teléfono",
                    value = phone,
                    keyboardType = KeyboardType.Phone,
                    isValid = validPhone,
                    onValueChange = { viewModel.onInputChange(Inputs.Phone, it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                KronosInput(
                    title = "Dirección",
                    value = address,
                    keyboardType = KeyboardType.Text,
                    isValid = validAddress,
                    onValueChange = { viewModel.onInputChange(Inputs.Address, it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                KronosInput(
                    title = "Comentarios",
                    value = comments,
                    keyboardType = KeyboardType.Text,
                    isValid = true,
                    onValueChange = { viewModel.onInputChange(Inputs.Comments, it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterHorizontally)
                ) {
                    OutlinedButton(onClick = { viewModel.onCancelar() }) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = { viewModel.onGuardar() },
                        enabled = enableGuardar,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0XFF82284E),
                            disabledContentColor = Color(0xFFC393A7)
                        )
                    ) {
                        Text(if (cargando) "Guardando " else "Guadar Restaurant")
                    }
                }
            }
        //}

        item {
            Button(
                onClick = {viewModel.onNuevoRestaurant()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFF82284E),
                    disabledContentColor = Color(0xFFC393A7)
                )
            ) {
                Text("Nuevo Restaurante")
            }
        }

        // CAMBIO 4: Los restaurantes como items individuales
        items(restaurantes) { restaurant ->
            RestauranteListItem(
                restaurant = restaurant,
                onEditClick = { viewModel.onEditClick(restaurant) },
                onDeleteClick = { viewModel.onDeleteClick(restaurant) },
                onSelectClick = { viewModel.onSelectClick(restaurant) }
            )
        }
    }





}
//name string phone string addres sstring comments string

