package com.example.mviexample.ui.main

import com.example.mviexample.data.api.RestApiImpl
import com.example.mviexample.data.api.RetrofitBuilder
import com.example.mviexample.data.model.TodoTask
import com.example.mviexample.ui.main.intent.MainIntent
import com.example.mviexample.ui.main.state.MainState

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mviexample.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var button_tasks:Button
    lateinit var recyclerview_tasks: RecyclerView
    lateinit var progressbar: ProgressBar

    private val mainActivityViewModel: MainActivityViewModel by viewModels { MainViewModelFactory(
        RestApiImpl(RetrofitBuilder.apiService)
    )}
    private var mainAdapter = MainAdapter(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_tasks = findViewById(R.id.button_tasks)
        recyclerview_tasks = findViewById(R.id.recyclerview_tasks)
        progressbar = findViewById(R.id.progressbar)
        setupUI()
        setupClicks()
        observeViewModel()
    }
    private fun setupClicks(){

        button_tasks.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.userIntent.send(MainIntent.FetchTodoTasks)
            }
        }
    }

    private fun setupUI(){

        recyclerview_tasks.layoutManager = LinearLayoutManager(this)

        recyclerview_tasks.run {
            adapter = mainAdapter
        }
    }

    /*Este método modifica la vista según el estado del modelo*/
    private fun observeViewModel(){
        lifecycleScope.launch {
            mainActivityViewModel.mainState.collect {mainState->

                when(mainState){
                    is MainState.Loading -> progressbar.visibility = View.VISIBLE

                    is MainState.LoadTasks -> {
                        progressbar.visibility = View.GONE
                        renderList(mainState.todoTasks)
                    }
                    is MainState.Error -> {
                        recyclerview_tasks.visibility = View.GONE
                        Toast.makeText(applicationContext, "Error: ${mainState.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderList(listTodoTask: List<TodoTask>){
        mainAdapter.setTodoTasks(listTodoTask)
        mainAdapter.notifyDataSetChanged()
    }
}