package com.example.alcometerapp

import androidx.lifecycle.LiveData
import com.example.alcometerapp.ui.profile.Profile
import com.example.alcometerapp.ui.profile.ProfileDao
import com.example.alcometerapp.ui.promiles.Result
import com.example.alcometerapp.ui.promiles.ResultDao

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