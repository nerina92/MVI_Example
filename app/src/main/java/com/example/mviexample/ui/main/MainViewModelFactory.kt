package com.example.mviexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mviexample.data.api.RestApi
import com.example.mviexample.data.repository.MainRepository

class MainViewModelFactory(private val restApi: RestApi) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(MainRepository(restApi)) as T

        throw IllegalArgumentException("Unknown class name")
    }
}