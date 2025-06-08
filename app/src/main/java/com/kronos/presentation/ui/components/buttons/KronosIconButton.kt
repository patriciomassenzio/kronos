package com.kronos.presentation.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.presentation.ui.theme.ButtonColors

@Preview
@Composable
fun preview(){
    Column {

        KronosIconButton(
            text = "Google",
            image = R.drawable.google,


            )
        Spacer(modifier = Modifier.width(24.dp))
        KronosIconButton(
            text = "Apple ID",
            image = R.drawable.apple
        )
        Spacer(modifier = Modifier.width(24.dp))
        KronosIconButton(
            text = "Microsoft",
            image = R.drawable.microsoft
        )
    }
}

@Composable
fun KronosIconButton(
    text: String,
    @DrawableRes image: Int,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        IconButton(
            onClick = { onClick?.invoke() },
            modifier = Modifier.size(50.dp)
                .background(color = ButtonColors.background, shape = RoundedCornerShape(100.dp))
        ) {
            Icon(painter = painterResource(image), contentDescription = "Home", tint = ButtonColors.text)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = ButtonColors.description
        )
    }
}