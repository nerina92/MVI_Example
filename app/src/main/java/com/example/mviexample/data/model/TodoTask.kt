package com.example.mviexample.data.model

data class TodoTask (
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
) {

}