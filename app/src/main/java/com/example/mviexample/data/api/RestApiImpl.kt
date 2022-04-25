package com.example.mviexample.data.api

import com.example.mviexample.data.model.TodoTask

/*implementa la interfaz RestApi
En el constructor de la clase añadiremos como parametro el RestApiService e implementaremos el
método de RestApi, de esta forma haremos un override y
llamaremos devolveremos el resultado del servicio.*/
class RestApiImpl (private val restApiService: RestApiService): RestApi {

    override suspend fun getTodoTasks(): List<TodoTask> {
        return restApiService.listTodo()
    }
}