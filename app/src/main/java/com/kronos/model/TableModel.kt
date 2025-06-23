package com.kronos.model

data class TableModel(
    val id: String,
    val created_at : String,
    val update_at : String,
    val is_delete : Boolean = false,
    val table_number: String,
    val capacity: Int ,
    val status: String,
    val orders: OrdersModel,
    val assigned_employee: EmployeesLogin,
    val room_id: String,
    val x: Int,
    val y: Int,
    val type: String,
)
