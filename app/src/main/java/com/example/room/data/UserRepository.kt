package com.example.room.data

import android.util.Log
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    suspend fun readAllData():List<User>{
        return userDao.readAllData()
    }

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}