package com.example.alcometerapp.ui.viewmodel

import androidx.lifecycle.LiveData
import com.example.alcometerapp.database.Profile
import com.example.alcometerapp.database.ProfileDao
import com.example.alcometerapp.database.Result
import com.example.alcometerapp.database.ResultDao

class Repository constructor(
    private val profileDao: ProfileDao,
    private val resultDao: ResultDao
) {
    fun getProfile(): Profile {
        //refreshUser(userId)
        val cached: Profile = profileDao.get()
        if(cached!=null){
            return cached
        }
        return cached;
        //API
    }

    fun getLastResult(): Result {
        val cached: Result = resultDao.get()
        if(cached!=null){
            return cached
        }
        return cached;
        //API
    }

    fun getAllResults(): LiveData<List<Result>> {
        val cached: LiveData<List<Result>> = resultDao.getAll()
        if(cached!=null){
            return cached
        }
        return cached;
        //API
    }

    fun updateResult(result: Result){
        resultDao.update(result);
    }

    fun insertResult(result: Result){
        resultDao.insert(result);
    }

    fun updateProfile(profile: Profile){
        profileDao.update(profile);
    }

    fun insertProfile(profile: Profile){
        profileDao.insert(profile);
    }

    fun deleteResult(result : Result){
        resultDao.delete(result)
    }

}