package com.example.alcometerapp

import androidx.lifecycle.*
import com.example.alcometerapp.ui.profile.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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


//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    private val _hasProfile = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    val hasProfile: LiveData<Boolean?>
        get() = _hasProfile

    //var userName: LiveData<String> = _userName;

    //val user = profileRepository.getUser(userId).asLiveData()

//    private fun getUserFromDatabase(): User? {
//        var user = database.getUser()
////        if (night?.endTimeMilli != night?.startTimeMilli) {
////            night = null
////        }
//        return user
//    }

    init {
        initializeUser()
        _hasProfile.value=true;
    }

    private fun initializeUser() {
        viewModelScope.launch {
            profile.value = repository.getProfile()
            profileName.value = profile.value?.name;
            gender.value = profile.value?.gender;
            growth.value = profile.value?.growth;
            weight.value = profile.value?.weight
        }
    }
    fun update(){
        if(profile.value == null){
            val newProfile = Profile(name = profileName.toString(), gender = gender.toString(), growth = growth.value?: 0, weight = weight.value?: 0)
        }
        profile.value?.name = profileName.value?: ""
        profile.value?.gender = gender.value?: "man"
        profile.value?.growth = growth.value?: 0
        profile.value?.weight = weight.value?: 0

        val profile = profile.value?: return
        repository.updateProfile(profile)
    }

    fun insert() {
        viewModelScope.launch {
            val newProfile = Profile(name = "j", gender = "a", growth = 150, weight = 70)
            repository.insertProfile(newProfile)
            //profile.value = repository.getProfile()
        }

    }
}