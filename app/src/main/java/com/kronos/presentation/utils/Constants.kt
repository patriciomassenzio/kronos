package com.kronos.presentation.utils

class Constants {
    companion object {
        const val BASE_URL = "https://back-kronos.onrender.com/"
        const val LOCAL_URL = "http://10.0.2.2:8080/"

        const val USERS = "${BASE_URL}users/"
        const val USER_ID = "${USERS}{userId}"

        const val EMPLOYEES = "${BASE_URL}employees/"
        const val EMPLOYEE_LOGIN = "${EMPLOYEES}login/"
        const val EMPLOYEE_ID = "${EMPLOYEES}{employee_id}/"
        const val EMPLOYEE_TABLE = "${EMPLOYEE_ID}table/"

        const val RESTAURANTS = "${BASE_URL}RESTAURANTES/"
        const val RESTAURANT_ID = "${RESTAURANTS}RESTAURANT_ID/"


        const val TABLES = "${BASE_URL}TABLES/"
        const val TABLE_ID = "${TABLES}table_id/"
        const val TABLE_ASSIGN = "${TABLE_ID}assing/"
        const val TABLE_UNASSIGN = "${TABLE_ID}unassing/"
        const val TABLE_CLOSE = "${TABLE_ID}close/"
        const val TABLE_AVAILABLE= "${TABLE_ID}available/"

        const val TOKEN = "${BASE_URL}TOKEN/"
        const val LOGIN = "${TOKEN}LOGIN/"
        const val LOGIN_fORM = "${TOKEN}LOGIN_FORM/"

        const val ORDERS = "${BASE_URL}ORDERS/"
        //const val TOKEN = "${ORDERS}TOKEN/"










        const val LOGIN_URL = "api/v1/token"
        const val REGISTER_PIN_URL = "api/v1/employees"
        const val EMPLOYEES_LOGIN = "api/v1/employees/login"



    }
}