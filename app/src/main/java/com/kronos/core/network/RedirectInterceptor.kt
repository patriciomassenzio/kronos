package com.kronos.core.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RedirectInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Manejar redirección 307
        if (response.code == 307) {
            val location = response.header("Location")
            if (location != null) {
                Log.d("RedirectInterceptor", "Redirigiendo a: $location")

                // Crear nueva solicitud con la URL de redirección
                val newRequest = request.newBuilder()
                    .url(location)
                    .build()

                // Es importante cerrar la respuesta anterior para liberar recursos
                response.close()

                // Proceder con la nueva solicitud
                return chain.proceed(newRequest)
            }
        }

        return response
    }
}