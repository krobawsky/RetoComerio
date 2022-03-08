package com.krobawsky.retocomercio.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krobawsky.retocomercio.data.model.UserModel
import com.krobawsky.retocomercio.domain.GetUsersUseCase
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userListModel = MutableLiveData<List<UserModel>>()
    val isLoading = MutableLiveData<Boolean>()

    var getUsersUseCase = GetUsersUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getUsersUseCase()

            if(!result.isNullOrEmpty()){
                userListModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}