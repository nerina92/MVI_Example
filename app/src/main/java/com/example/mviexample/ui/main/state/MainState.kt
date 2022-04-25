package com.example.mviexample.ui.main.state

import com.example.mviexample.data.model.TodoTask

//Este modelo tiene 4 estados
sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class LoadTasks(val todoTasks: List<TodoTask>): MainState()
    data class Error(val error: String?): MainState()
}