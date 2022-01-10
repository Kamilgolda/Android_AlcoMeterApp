package com.example.alcometerapp

import com.example.alcometerapp.ui.profile.Profile
import com.example.alcometerapp.ui.profile.ProfileDao

class Repository constructor(
    private val profileDao: ProfileDao
) {
    fun getProfile(): Profile {
        //refreshUser(userId)
        val cached: Profile = profileDao.get()
        if(cached!=null){
            return cached
        }
        return cached;
        //API
        //return Profile(gender = "", growth = 150, weight = 70)
    }

    fun updateProfile(profile: Profile){
        profileDao.update(profile);
    }
//
//    private fun refreshUser(userId: String){
//        val userExists = userDao.load(userId)
////        if(!userExists){
////
////        }
//    }

    fun insertProfile(profile: Profile){
        profileDao.insert(profile);
    }

}