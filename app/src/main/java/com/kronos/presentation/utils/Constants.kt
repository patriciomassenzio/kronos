package com.kronos.presentation.utils

class Constants {
    companion object {
        const val BASE_URL = "https://back-kronos.onrender.com/"
        const val LOCAL_URL = "http://10.0.2.2:8080/"

        const val USERS = "${BASE_URL}users/"
        const val USER_ID = "${USERS}{userId}"

        const val EMPLOYEES = "${BASE_URL}employees/"
        const val EMPLOYEES_LOGIN = "${EMPLOYEES}login/"
        const val EMPLOYEE_ID = "${EMPLOYEES}{employee_id}/"
        const val EMPLOYEE_TABLE = "${EMPLOYEE_ID}table/"

        const val RESTAURANTS = "${BASE_URL}restaurants/"
        const val RESTAURANT_ID = "${RESTAURANTS}restaurant_id/"


        const val TABLES = "${BASE_URL}table/"
        const val TABLE_ID = "${TABLES}table_id/"
        const val TABLE_ASSIGN = "${TABLE_ID}assing/"
        const val TABLE_UNASSIGN = "${TABLE_ID}unassing/"
        const val TABLE_CLOSE = "${TABLE_ID}close/"
        const val TABLE_AVAILABLE= "${TABLE_ID}available/"

        const val TOKEN = "${BASE_URL}token/"
        const val LOGIN = "${TOKEN}login/"
        const val LOGIN_fORM = "${TOKEN}login_form/"

        const val ORDERS = "${BASE_URL}orders/"
        const val ORDERS_ID = "${ORDERS}orders_id/"

        const val MENUS = "${BASE_URL}menus/"
        const val MENU_ID = "${MENUS}menu_id/"

        const val DAILY_MENU = "${BASE_URL}daily_menu/"
        const val DAILY_MENU_ID = "${DAILY_MENU}daily_menu_id/"

        const val ROOMS = "${BASE_URL}rooms/"
        const val ROOM_ID = "${ROOMS}room_id/"

        const val STOCK = "${BASE_URL}stock/"
        const val STOCK_ID = "${STOCK}stock_id/"

        const val FINAL_ARTICLES = "${BASE_URL}final_articles/"
        const val FINAL_ARTICLES_ID  = "${FINAL_ARTICLES}final_articles_id/"

        const val COMPONENTS = "${BASE_URL}components/"
        const val COMPONENT_ID = "${COMPONENTS}component_id/"

        const val OPTIONS = "${BASE_URL}options/"
        const val OPTION_ID = "${OPTIONS}option_id/"

        const val GENERAL_MENU = "${BASE_URL}general_menu/"
        const val GENERAL_MENU_ID = "${GENERAL_MENU}general_menu_id/"


        const val INGREDIENTS = "${BASE_URL}ingredients/"
        const val INGREDIENT_ID = "${INGREDIENTS}ingredient_id/"

        const val CATEGORIES_MENU = "${BASE_URL}categories_menu/"
        const val CATEGORIES_MENU_ID = "${CATEGORIES_MENU}categories_menu_id/"
    }
}