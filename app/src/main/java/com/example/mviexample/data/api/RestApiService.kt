package com.example.mviexample.data.api

import com.example.mviexample.data.model.TodoTask
import retrofit2.http.GET

interface RestApiService {

    @GET("todos")
    suspend fun listTodo(): List<TodoTask>

}