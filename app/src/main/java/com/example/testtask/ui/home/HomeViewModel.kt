package com.example.testtask.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.ui.home.model.HomeDataDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
}

class HomeViewModel : ViewModel() {

    private val _data = MutableStateFlow<Resource<HomeDataDto>>(Resource.Loading)
    val data: StateFlow<Resource<HomeDataDto>> = _data.asStateFlow()


    fun loadData(context: Context) {
        viewModelScope.launch {
            _data.value = Resource.Loading
            try {
                delay(2_000)
                val items = loadDataFromAssets(context)
                if (items != null) {
                    _data.value = Resource.Success(items)
                } else {
                    _data.value = Resource.Error(Exception("data is null"))
                }
            } catch (e: Exception) {
                _data.value = Resource.Error(e)
            }
        }
    }

    private fun loadDataFromAssets(context: Context): HomeDataDto? {
        try {
            val jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
            val listType = object : TypeToken<HomeDataDto>() {}.type
            val data = Gson().fromJson<HomeDataDto>(jsonString, listType)
            return data
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}