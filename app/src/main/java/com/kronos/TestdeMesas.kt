//package com.kronos
//
//import android.util.Log
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.gestures.detectTransformGestures
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.size
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.derivedStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableFloatStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.DrawScope
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import com.kronos.enumeration.TipoMesa
//import com.kronos.model.Mesa
//import com.kronos.model.MesaBarra
//import com.kronos.model.MesaCuadrada
//import com.kronos.model.MesaOvalada
//import com.kronos.model.MesaRedonda
//import com.kronos.presentation.ui.views.homeview.salon.SalonView
//import kotlin.math.roundToInt
//import kotlin.random.Random
//
//
//@Preview
//@Composable
//fun SalonCuadriculaPreview() {
//    SalonView(emptyList(), onMesaClick = {})
//}
//@Composable
//fun generarMesas(): List<Mesa> {
//    val mesas = mutableListOf<Mesa>()
//    val tiposMesa = TipoMesa.entries.toTypedArray()
//
//    for (i in 1..20) {
//        // Crear una posición aleatoria (puedes modificar los rangos según necesites)
//        val posicionAleatoria = Offset(Random.nextFloat() * Random.nextInt(1, 700), Random.nextFloat() * Random.nextInt(1, 700))
//
//        // Escoger un tipo de mesa aleatorio
//        val tipoMesa = TipoMesa.MesaBarra// tiposMesa[Random.nextInt(0, 3)]//
//
//        // Crear mesa según el tipo
//        val mesa = when (tipoMesa) {
//            TipoMesa.MesaCuadrada -> MesaCuadrada(
//                id = i,
//                tipoMesa = tipoMesa,
//                posicion = mutableStateOf(posicionAleatoria),
//                color = mutableStateOf(Color.Gray),
//                alto = Random.nextInt(1, 4),
//                ancho = Random.nextInt(1, 4)
//            )
//            TipoMesa.MesaRedonda -> MesaRedonda(
//                id = i,
//                tipoMesa = tipoMesa,
//                posicion = mutableStateOf(posicionAleatoria),
//                color = mutableStateOf(Color.Gray),
//                sillas = Random.nextInt(2, 8)
//            )
//            TipoMesa.MesaOvalada -> MesaOvalada(
//                id = i,
//                tipoMesa = tipoMesa,
//                posicion = mutableStateOf(posicionAleatoria),
//                color = mutableStateOf(Color.Gray),
//                alto = Random.nextInt(1, 4),
//                ancho = Random.nextInt(1, 4)
//            )
//            TipoMesa.MesaBarra -> MesaBarra(
//                id = i,
//                tipoMesa = tipoMesa,
//                posicion = mutableStateOf(posicionAleatoria),
//                color = mutableStateOf(Color.Gray),
//                lado = Random.nextInt(0, 3)
//
//            )
//            TipoMesa.MesaPos -> com.kronos.model.MesaPos(
//                id = i,
//                tipoMesa = tipoMesa,
//                posicion = mutableStateOf(posicionAleatoria)
//
//            )
//        }
//
//        mesas.add(mesa)
//    }
//
//    return mesas
//}
//
//fun DrawScope.drawGrid(size: Size, offset: Offset, zoom: Float) {
//    val gridSize = 50f * zoom
//    val startX = offset.x % gridSize
//    val startY = offset.y % gridSize
//
//    // Dibujar las líneas verticales
//    for (x in startX.toInt() until size.width.toInt() step gridSize.toInt()) {
//        drawLine(
//            color = Color(0xFF222222),
//            start = Offset(x.toFloat(), 0f),
//            end = Offset(x.toFloat(), size.height),
//            strokeWidth = .1f
//        )
//    }
//
//    // Dibujar las líneas horizontales
//    for (y in startY.toInt() until size.height.toInt() step gridSize.toInt()) {
//        drawLine(
//            color = Color(0xFF222222),
//            start = Offset(0f, y.toFloat()),
//            end = Offset(size.width, y.toFloat()),
//            strokeWidth = .1f
//        )
//    }
//}

//
//
//@Composable
//fun SalonCuadricula(){
//    val tamanoCuadriculaBase = 40.dp
//    var tamanoCuadricula by remember { mutableStateOf(40.dp) }
//    var screenHeightDp by remember { mutableStateOf(0.dp) }
//    var screenWidthDp by remember { mutableStateOf(0.dp) }
//    var scale by remember { mutableStateOf(1f) }
////    var rows by remember { mutableStateOf(0) }
////    var columns by remember { mutableStateOf(0) }
//    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
//
//
//    val screenConfig = LocalConfiguration.current
//    val density = LocalDensity.current
//    //calcular rows y colums segun tamaño de la pantalla
//    screenWidthDp = screenConfig.screenWidthDp.dp
//    screenHeightDp =  screenConfig.screenHeightDp.dp
//
//    val rows by remember {
//        derivedStateOf { (screenHeightDp / tamanoCuadricula).roundToInt() }
//    }
//    val columns by remember {
//        derivedStateOf { (screenWidthDp / tamanoCuadricula).roundToInt() }
//    }
//
//    Log.d("tamano pantalla", screenHeightDp.toString())
//    Log.d("rows", rows.toString())
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ){
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .graphicsLayer(
//                    scaleX = scale, scaleY = scale,
//                    translationX = offset.x, translationY = offset.y
//                )
//                .pointerInput(Unit){
//                    detectTransformGestures { centroid, pan, zoom, rotation ->
//                        offset = Offset(offset.x + pan.x, offset.y + pan.y)
//                        Log.d("zoom", zoom.toString())
//                        scale = (scale * zoom)
//                        tamanoCuadricula = tamanoCuadricula * scale
//                    }
//                }
//        ){
//            for (i in 0 until rows ){
//                Row() {
////                    Box(
////                        modifier = Modifier
////                            .size(tamanoCuadricula)
////                            .background(color = Color(0xFFB0B0B0))
////                            .border(1.dp, Color.Black)
////                    )
//                    for (j in 0 until  columns){
//                        Box(
//                            modifier = Modifier
//                                .size(tamanoCuadricula)
//                                .background(color = Color(0xFFB0B0B0))
//                                .border(1.dp, Color.Black)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
