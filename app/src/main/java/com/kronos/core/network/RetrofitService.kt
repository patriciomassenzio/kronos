package com.kronos.core.network

import android.content.Context

class RetrofitService(context: Context) {
    private val retrofit = RetrofitClient.getRetrofit(context)
    val apiService: KronosApi = retrofit.create(KronosApi::class.java)
}
