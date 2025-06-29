package com.kronos.core.network

import com.kronos.model.EmployeesLogin
import com.kronos.model.OrdersModel
import com.kronos.model.Restaurant
import com.kronos.model.TableModel
import com.kronos.presentation.ui.views.auth.login.LoginModel
import com.kronos.model.UserModel
import com.kronos.model.UserEmployeeModel
import com.kronos.model.responses.LoginResponse
import com.kronos.navigation.Routes
import com.kronos.presentation.utils.Constants.Companion.EMPLOYEES
import com.kronos.presentation.utils.Constants.Companion.EMPLOYEES_LOGIN
import com.kronos.presentation.utils.Constants.Companion.ORDERS
import com.kronos.presentation.utils.Constants.Companion.RESTAURANTS
import com.kronos.presentation.utils.Constants.Companion.RESTAURANT_ID
import com.kronos.presentation.utils.Constants.Companion.TABLES
import com.kronos.presentation.utils.Constants.Companion.USERS
import com.kronos.presentation.utils.Constants.Companion.USER_ID
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface KronosApi {
    //USERS
    @POST(USERS)
    fun usersPost(@Body request: UserModel): Call<UserModel>
    @GET(USERS)
    fun usersGet( request: UserModel): Call<UserModel>
    @GET(USER_ID)
    fun usersGet(user_id: String): Call<UserModel>

    //TOKEN
    @POST("api/v1/token")
    suspend fun login(@Body request: LoginModel): Response<LoginResponse>

    //TOKEN
    @GET(TABLES)
    fun table(@Body request: TableModel): Call<TableModel>

    //ORDERS
    @POST(ORDERS)
    fun orders(@Body request: OrdersModel): Call<OrdersModel>


    //EMPLOYEES
    @POST(EMPLOYEES)
    fun registerPin(@Body request: UserEmployeeModel): Call<UserEmployeeModel>
    @POST(EMPLOYEES_LOGIN)
    fun employeesLogin(@Body request: EmployeesLogin):  Response<EmployeesLogin>

    //Restaurante
    @POST(RESTAURANTS)
     suspend fun restaurants(@Body request: Restaurant): Response<Restaurant>
    @GET()
    fun restaurantGet( request: Restaurant):  Response<Restaurant>
    @DELETE("restaurante/{id}")
    suspend fun deleteRestaurants(@Path("id") id: String): Response<Unit>


//    @POST("employees/login") // o el endpoint que uses para login con PIN
//    fun loginWithPin(@Body request: PinLoginRequest): Call<UserEmployeeModel>
//    @POST("employees/register") // endpoint para registrar empleado
//    fun registerPin(@Body userEmployee: UserEmployeeModel): Call<UserEmployeeModel>



//    @POST("api/v1/employees/")
//     fun registerEmployee(@Body employee: UserEmployeeModel): Response<Unit>
//    abstract fun loginWithPin(pin: String): Response<UserEmployeeModel>
    companion object

}