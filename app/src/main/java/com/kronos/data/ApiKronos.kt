package com.kronos.data

import com.kronos.model.EmployeesLogin
import com.kronos.presentation.ui.views.login.LoginModel
import com.kronos.model.UserModel
import com.kronos.model.UserPinModel
import com.kronos.presentation.ui.views.login.data.network.response.LoginResponse
import com.kronos.presentation.utils.Constants.Companion.EMPLOYEES_LOGIN
import com.kronos.presentation.utils.Constants.Companion.LOGIN_URL
import com.kronos.presentation.utils.Constants.Companion.REGISTER_PIN_URL
import com.kronos.presentation.utils.Constants.Companion.USERS
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiKronos {

    @POST(USERS)
    fun users(@Body request: UserModel): Call<UserModel>

    @POST(LOGIN_URL)
    fun login(@Body request: LoginModel): Call<LoginResponse>

    @POST(REGISTER_PIN_URL)
    fun registerPin(@Body request: UserPinModel): Call<UserPinModel>

    @POST(EMPLOYEES_LOGIN)
    fun employeesLogin(@Body request: EmployeesLogin): Call<EmployeesLogin>

}