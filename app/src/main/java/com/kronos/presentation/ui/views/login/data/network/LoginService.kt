package com.kronos.presentation.ui.views.login.data.network

import android.content.Context
import com.kronos.core.network.RetrofitHelper
import com.kronos.data.ApiKronos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService(context: Context) {
    private val retrofit = RetrofitHelper.getRetrofit(context)

    val apiService: ApiKronos = retrofit.create(ApiKronos::class.java)

}