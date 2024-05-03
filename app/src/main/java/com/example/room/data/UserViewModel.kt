package com.example.room.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var readAllData : List<User>
    private val repository: UserRepository
    private var _readData = MutableLiveData<List<User>>()
    var readData :LiveData<List<User>> = _readData

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        setData()
    }

    private fun setData(){
        viewModelScope.launch {
            readAllData = repository.readAllData()
            _readData.postValue(repository.readAllData())
        }
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}