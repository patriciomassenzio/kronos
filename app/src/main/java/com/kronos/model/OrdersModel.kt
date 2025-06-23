package com.kronos.model

import android.view.Menu
import androidx.compose.runtime.MutableState

data class OrdersModel(
    val id: String,
    val created_at : String,
    val update_at : String,
    val is_delete : Boolean = false,
    val restaurant_id : String,
    val table_id : String,
    val menu_items: MutableList<Menu>,
    val menu_groups: MutableList<Menu>,
    val assigned_employee: String,
    val closed_order_at : String,
    val comments: String,
    val amount: Int,
    val status: String = "open",
    val order_type: String = "dine_in",

)
