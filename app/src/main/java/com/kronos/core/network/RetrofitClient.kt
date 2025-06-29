package com.kronos.core.network

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import com.kronos.data.datastore.AuthInterceptor
import com.kronos.data.datastore.StoreToken

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val BASE_URL = URL

    /**
     * Crea y configura una instancia de Retrofit con los timeouts necesarios
     * y el interceptor de autenticación.
     *
     * @param context Contexto necesario para acceder a DataStore
     * @return Instancia configurada de Retrofit
     */
    fun getRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(getOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Crea y configura un cliente OkHttp con los interceptores y timeouts necesarios
     *
     * @param context Contexto necesario para el interceptor de autenticación
     * @return Cliente OkHttp configurado
     */
    private fun getOkHttpClient(context: Context): OkHttpClient {
        // Crear StoreToken para manejar el token desde DataStore
        val storeToken = StoreToken(context)
        // // Añadir la instancia del nuevo interceptor
        val redirectInterceptor = RedirectInterceptor()
        // Crear AuthInterceptor
        val authInterceptor = AuthInterceptor(storeToken)

        // Configurar y devolver el cliente OkHttp
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(redirectInterceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    /**
     * Crea una instancia de la API especificada usando Retrofit
     *
     * @param context Contexto necesario para Retrofit
     * @return Instancia de la API configurada
     */
    inline fun <reified T> createApi(context: Context): T {
        return getRetrofit(context).create(T::class.java)
    }
}

//package com.kronos.core.network
//
//
//import android.content.Context
//import android.util.Log
//import com.kronos.data.datastore.AuthInterceptor
//import com.kronos.data.datastore.StoreToken
//import com.kronos.presentation.utils.Constants.Companion.BASE_URL
//import com.kronos.presentation.utils.Constants.Companion.URL
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RetrofitHelper {
//
////    fun getRetrofit(): Retrofit {
////        return Retrofit.Builder()
////            .baseUrl(BASE_URL)
////            .addConverterFactory(GsonConverterFactory.create())
////            //.client(getCliente())
////            //.client()
////            .build()
////    }
//
//    fun getRetrofit(context: Context): Retrofit {
//
//        // Crear StoreToken para manejar el token desde DataStore
//        val storeToken = StoreToken(context)
//
//        // Crear AuthInterceptor y el cliente OkHttp
//        val authInterceptor = AuthInterceptor(storeToken)
////        val okHttpClient = OkHttpClient.Builder()
////            .addInterceptor(authInterceptor) // Añadir el interceptor
////            .build()
//
//        val okHttpClient = OkHttpClient.Builder()
//            .followRedirects(true)  // Seguir redirecciones HTTP (como 301, 302)
//            .followSslRedirects(true)  // Seguir redirecciones HTTPS
//            .connectTimeout(2, TimeUnit.MINUTES)  // Timeout para la conexión
//            .readTimeout(2, TimeUnit.MINUTES)     // Timeout para la lectura
//            .writeTimeout(2, TimeUnit.MINUTES)    // Timeout para la escritura
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}
//
////private fun getCliente(): OkHttpClient {
////    val client = OkHttpClient.Builder()
////    .addInterceptor(AuthInterceptor)
////        .build()
////    return client
////
////}