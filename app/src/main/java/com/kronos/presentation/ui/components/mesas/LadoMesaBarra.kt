package com.kronos.presentation.ui.components.mesas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R

@Preview
@Composable
fun LadoMesabarraPreview(){
    LadoMesabarra(modifier = Modifier)
}

@Composable
fun LadoMesabarra(
    modifier: Modifier,


){
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = modifier
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp, 30.dp)
                    .background(color = Color(0xffB0B0B0), shape = RoundedCornerShape(100.dp))

            )
            Image(
                painter = painterResource(R.drawable.ic_siilla_barra),
                contentDescription = "",
                modifier = Modifier.width(35.dp)
            )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            modifier = modifier
                .graphicsLayer(rotationZ = 90f)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp, 30.dp)
                    .background(color = Color(0xFF8FCB9B), shape = RoundedCornerShape(100.dp))

            )
            Image(
                painter = painterResource(R.drawable.ic_siilla_barra),
                contentDescription = "",
                modifier = Modifier.width(35.dp)
            )
            Box(){

                Text(text = " 1")

            }


        }
//        Spacer(modifier = Modifier.height(25.dp))
//        Column(
//            modifier = modifier
//                .graphicsLayer(rotationZ = 270f)
//                .background(Color.White),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(60.dp, 30.dp)
//                    .background(color = Color(0xffB0B0B0), shape = RoundedCornerShape(100.dp))
//
//            )
//            Image(
//                painter = painterResource(R.drawable.ic_siilla_barra),
//                contentDescription = "",
//                modifier = Modifier.width(35.dp)
//            )
//
//
//        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Column(
//            modifier = modifier
//                .graphicsLayer(rotationZ = 180f)
//                .background(Color.White),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(60.dp, 30.dp)
//                    .background(color = Color(0xffB0B0B0), shape = RoundedCornerShape(100.dp))
//
//            )
//            Image(
//                painter = painterResource(R.drawable.ic_siilla_barra),
//                contentDescription = "",
//                modifier = Modifier.width(35.dp)
//            )
//
//        }

        Text(text = "hacer un popup, poner en pantalla las cuatras mesas con su respectivos lados , ")
    }


}

@Composable
fun NumberoSilla(
    numero: String
){
    Text(
        text = numero,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )

}