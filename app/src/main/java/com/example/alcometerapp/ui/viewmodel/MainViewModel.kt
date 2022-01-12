package com.example.alcometerapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.alcometerapp.database.Profile
import com.example.alcometerapp.database.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    var profile = MutableLiveData<Profile?>()

    val profileName = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
    val growth = MutableLiveData<Int>()
    val weight = MutableLiveData<Int>()

    var consumed = MutableLiveData<Result?>()

    val strength = MutableLiveData<String>()
    val portion = MutableLiveData<String>()
    val quantity = MutableLiveData<String>()
    val startDate = MutableLiveData<Date>()
    val endDate = MutableLiveData<Date>()

    private val _hasProfile = MutableLiveData<Boolean?>()

    val hasProfile: LiveData<Boolean?>
        get() = _hasProfile

    val consumedList = repository.getAllResults()

    init {
        initializeUser()
        _hasProfile.value = profile.value?.userId != null;
    }

    private fun initializeUser() {
        profile.value = repository.getProfile()
        if(profile.value?.userId != null) {
            profileName.value = profile.value?.name;
            gender.value = profile.value?.gender;
            growth.value = profile.value?.growth;
            weight.value = profile.value?.weight
        }
        else{
            gender.value = "man"
            growth.value = 150
            weight.value = 70
        }
    }

    fun updateProfile(){
        if(profile.value?.userId == null){
            val newProfile = Profile(name = profileName.value?: "", gender = gender.value?: "man", growth = growth.value?: 0, weight = weight.value?: 0)
            repository.insertProfile(newProfile)
            initializeUser()
            return
        }
        profile.value?.name = profileName.value?: ""
        profile.value?.gender = gender.value?: "man"
        profile.value?.growth = growth.value?: 0
        profile.value?.weight = weight.value?: 0

        val profile = profile.value?: return
        repository.updateProfile(profile)
    }

    fun insertConsumed(){
        if(profile.value?.userId == null)
            return

        val newConsumed = Result(userId = profile.value!!.userId, strength = strength.value!!.toInt(), portion = portion.value!!.toInt(), quantity= quantity.value!!.toInt(), startDate = startDate.value!!, endDate = endDate.value!!)

        repository.insertResult(newConsumed);
    }

    fun deleteConsumed(result: Result){
        repository.deleteResult(result)
    }
}