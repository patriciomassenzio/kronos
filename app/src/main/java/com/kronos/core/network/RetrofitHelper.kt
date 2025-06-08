package com.kronos.core.network


import android.content.Context
import com.kronos.data.datastore.AuthInterceptor
import com.kronos.data.datastore.StoreToken
import com.kronos.presentation.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

//    fun getRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            //.client(getCliente())
//            //.client()
//            .build()
//    }

    fun getRetrofit(context: Context): Retrofit {

        // Crear StoreToken para manejar el token desde DataStore
        val storeToken = StoreToken(context)

        // Crear AuthInterceptor y el cliente OkHttp
        val authInterceptor = AuthInterceptor(storeToken)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // Añadir el interceptor
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Añadir el cliente OkHttp con el interceptor
            .addConverterFactory(GsonConverterFactory.create())
            //.client(getCliente())
            //.client()
            .build()
    }
}

//private fun getCliente(): OkHttpClient {
//    val client = OkHttpClient.Builder()
//    .addInterceptor(AuthInterceptor)
//        .build()
//    return client
//
//}