package com.example.mviexample.data.api

import com.example.mviexample.data.model.TodoTask

/*método que devolverá una lista de Tareas*/
interface RestApi {
    suspend fun getTodoTasks(): List<TodoTask>
}