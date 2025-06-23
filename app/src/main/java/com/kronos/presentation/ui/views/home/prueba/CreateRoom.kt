package com.kronos.presentation.ui.views.home.prueba

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kronos.presentation.ui.components.buttons.KronosFilledButton
import com.kronos.presentation.ui.components.inputs.ButtonDialog
import com.kronos.presentation.ui.theme.dark80

@Preview
@Composable
fun CreateRoomPreview(){
    CreateRoom()
}



@Composable
fun CreateRoom(

){
   Column(
       
       modifier = Modifier
           .fillMaxSize()
           .background(color = dark80)


   ) {
     Button(onClick = {}
     ) {
         Text(text = "guardar name string phone string addres sstring comments string")


     }
   }


}
