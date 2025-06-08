package com.kronos.data.datastore

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (private val storeToken: StoreToken) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Obtener el token usando el TokenProvider
        val token = runBlocking {
            storeToken.getToken.firstOrNull()
        }
            //tokenProvider.getToken()

        // Si el token existe, añadirlo al header "Authorization"
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}