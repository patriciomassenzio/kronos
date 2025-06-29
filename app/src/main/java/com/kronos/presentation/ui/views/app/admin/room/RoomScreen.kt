package com.kronos.presentation.ui.views.app.admin.room

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kronos.enumeration.Inputs
import com.kronos.model.Restaurant
import com.kronos.presentation.ui.components.inputs.KronosInput
import com.kronos.presentation.ui.theme.dark80
@Preview
@Composable
fun CreateRoomPreview(){
    RoomScreen(RoomViewModel(LocalContext.current) )
}

@Composable
fun RoomScreen(
    viewModel: RoomViewModel,
//    restaurantViewModel: RestaurantViewModel
) {
    var isSaving by rememberSaveable { mutableStateOf(false) }
    val numero: Int by viewModel.numero.observeAsState(0)
    val descipcion: String by viewModel.descripcion.observeAsState("")
    //variable o parametro para traer el id/ numero de rest
    val restaurantes: List<Restaurant> by viewModel.restaurantes.observeAsState(emptyList())
    val cargando: Boolean by viewModel.cargando.observeAsState(true)
    val validNumero: Boolean by viewModel.validNumero.observeAsState(true)
    val enableGuardar: Boolean by viewModel.enableGuardar.observeAsState(true)
    val enableCampos: Boolean by viewModel.enableCampos.observeAsState(false)

    Column(
        modifier = Modifier.fillMaxSize(1f).padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            KronosInput(
                title = "Room number",
                value = numero.toString(),
                keyboardType = KeyboardType.Number,
                isValid = validNumero,
                onValueChange = { viewModel.onInputChange(Inputs.Numero, it) },
                modifier = Modifier.fillMaxWidth()
            )
            KronosInput(
                title = "Description",
                value = descipcion,
                keyboardType = KeyboardType.Text,
                isValid = true,
                onValueChange = { viewModel.onInputChange(Inputs.Descripcion, it) },
                modifier = Modifier.fillMaxWidth()
            )


            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp),

            ) {
                OutlinedButton(
                    onClick = { viewModel.onCancelar() }
                ) {
                    Text("Cancelar")
                }
                Button(
                    onClick = { viewModel.onGuardar() },
                    enabled = enableGuardar,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFF82284E),
                        disabledContentColor = Color(0xFFF9F3F6)

                    ),

                ) {
                    Text(if (cargando) "Guardando... " else "Guardar Room")
                }
            }
        }
//               if(enableCampos){
//
//               }


//               Button(
//                   onClick = {viewModel.onNuevoRestaurant()},
//                   colors = ButtonDefaults.buttonColors(
//                       containerColor = Color(0XFF82284E),
//                       disabledContentColor = Color(0xFFC393A7)
//
//                   )
//               ) {
//                   Text("Nuevo Restaurante")
//               }
//               LazyColumn(
//                   modifier = Modifier.fillMaxSize(),
//                   contentPadding = PaddingValues(16.dp),
//                   verticalArrangement = Arrangement.spacedBy(16.dp)
//               ) {
//                   items(restaurantes) { restaurant ->
//                       RestauranteListItem(
//                           restaurant = restaurant,
//                           onEditClick = { viewModel.onEditClick(restaurant) },
//                           onDeleteClick = { viewModel.onDeleteClick(restaurant) },
//                           onSelectClick = { viewModel.onSelectClick(restaurant) }
//                       )
//                   }
//               }
    }




//       Button(
//           onClick = {
//               Log.d("CreateRoom", "si")
//               isSaving = true
//           },
//           enabled = !isSaving,
//           colors = ButtonDefaults.buttonColors(
//               containerColor = Color(0XFF82284E),
//               disabledContentColor = Color(0xFFC393A7)
//
//           )
//       ) {
//           Row(
//               verticalAlignment = Alignment.CenterVertically,
//               horizontalArrangement = Arrangement.Center,
//               modifier = Modifier.padding(8.dp)
//
//           ) {
//               if (isSaving) {
//                   CircularProgressIndicator(
//                       modifier = Modifier
//                           .size(20.dp)
//                           .padding(end = 8.dp),
//                       color = Color.White,
//                       strokeWidth = 2.dp
//                   )
//               } else {
//                   Icon(
//                       imageVector = Icons.Default.Save,
//                       contentDescription = "Guardar",
//                       modifier = Modifier
//                           .size(20.dp)
//                           .padding(end = 4.dp)
//                   )
//               }
//
//           }
//           Text(
//               if (isSaving) "Guardando... " else "Guadar Room",
//
//               )
//       }






//name string phone string addres sstring comments string