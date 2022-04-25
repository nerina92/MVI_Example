package com.example.mviexample.ui.main.intent

//En este caso el usuario solo tendrá una intención, conseguir nuevas tareas
sealed class MainIntent {
    object FetchTodoTasks: MainIntent()
}