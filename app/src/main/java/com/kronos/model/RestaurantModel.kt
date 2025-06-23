package com.kronos.model

data class RestaurantModel(
    val id: String,
    val created_at : String,
    val update_at : String,
    val is_delete : Boolean = false,
    val name: String,
    val phone: String,
    val address: String,
    val comments: String,

)
