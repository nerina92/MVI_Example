package com.example.mviexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviexample.data.repository.MainRepository
import com.example.mviexample.ui.main.intent.MainIntent
import com.example.mviexample.ui.main.state.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: MainRepository) : ViewModel()  {

    /*Escucharemos las intenciones del usuario, así que creamos una variable userIntent con un
    Channel para las coroutines.*/
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<MainState>(MainState.Idle)

    /* El Model se encarga de controlar los estados, así que para ello añadimos un MainState de tipo
    MutableStateFlow. */
    val mainState: StateFlow<MainState>
        get() = _mainState

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is MainIntent.FetchTodoTasks -> fetchTodoTasks()
                }
            }
        }
    }

    /*método donde manejaremos las intenciones del usuario, en la intención FetchTodoTask se
    activará el método fetchTodoTasks() que cambiará el estado del Model una vez reciba los datos.*/
    private suspend fun fetchTodoTasks(){
        viewModelScope.launch {
            _mainState.value = MainState.Loading

            _mainState.value = try{
                MainState.LoadTasks(repository.getTodoTasks())
            }catch (e: Exception){
                MainState.Error(e.message)
            }
        }
    }
}