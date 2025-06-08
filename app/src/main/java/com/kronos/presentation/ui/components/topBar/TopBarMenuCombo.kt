package com.kronos.presentation.ui.components.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kronos.R
import com.kronos.presentation.ui.theme.dark00
import com.kronos.presentation.ui.theme.dark80

@Composable
fun TopBarMenuCombo(){
    val tabs = listOf("Menú/Combo","Plato principal", "Bebida", "Postre","Café")
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .height(424.dp)
            .background(color = dark80)
//            .border(
//                width = 1.dp,
//                color = Color(0xFFDFB7C8),
//                shape = RoundedCornerShape(10.dp)
//            )

    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            containerColor = Color.Black,
            contentColor = Color.White,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(3.dp),
                    color = Color.White
                )
            }

        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal

                        )
                    }

                )

            }
            Icon(
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = "",
                tint = dark00,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { }
            )

        }
    }
}