package com.kronos.data

import com.kronos.model.EmployeesLogin
import com.kronos.model.OrdersModel
import com.kronos.model.TableModel
import com.kronos.presentation.ui.views.login.LoginModel
import com.kronos.model.UserModel
import com.kronos.model.UserPinModel
import com.kronos.presentation.ui.views.login.data.network.response.LoginResponse
import com.kronos.presentation.utils.Constants.Companion.EMPLOYEES
import com.kronos.presentation.utils.Constants.Companion.EMPLOYEES_LOGIN
import com.kronos.presentation.utils.Constants.Companion.ORDERS
import com.kronos.presentation.utils.Constants.Companion.TABLES
import com.kronos.presentation.utils.Constants.Companion.TOKEN
import com.kronos.presentation.utils.Constants.Companion.USERS
import com.kronos.presentation.utils.Constants.Companion.USER_ID
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiKronos {
    //USERS
    @POST(USERS)
    fun usersPost(@Body request: UserModel): Call<UserModel>
    @GET(USERS)
    fun usersGet(@Body request: UserModel): Call<UserModel>
    @GET(USER_ID)
    fun usersGet(user_id: String): Call<UserModel>

    //TOKEN
    @POST(TOKEN)
    fun login(@Body request: LoginModel): Call<LoginResponse>

    //TOKEN
    @GET(TABLES)
    fun table(@Body request: TableModel): Call<TableModel>

    //ORDERS
    @POST(ORDERS)
    fun orders(@Body request: OrdersModel): Call<OrdersModel>


    //EMPLOYEES
    @POST(EMPLOYEES)
    fun registerPin(@Body request: UserPinModel): Call<UserPinModel>
    @POST(EMPLOYEES_LOGIN)
    fun employeesLogin(@Body request: EmployeesLogin): Call<EmployeesLogin>

}