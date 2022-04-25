package com.example.mviexample.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*creamos una instnacia de retrofit que se encarga de crear una implementación API de los endpoints
que hemos definido en la interfaz RestApiService*/
object RetrofitBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: RestApiService = getRetrofit().create(RestApiService::class.java)
}