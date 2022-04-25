package com.example.mviexample.data.repository

import com.example.mviexample.data.api.RestApi

class MainRepository(private val restApi: RestApi) {

    suspend fun getTodoTasks() = restApi.getTodoTasks()
}