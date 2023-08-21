package com.example.projectjusandeposit.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectjusandeposit.AppState

class BlankViewModel : ViewModel() {
    val blankLiveData:MutableLiveData<AppState> = MutableLiveData()

    fun removeAll(){
        blankLiveData.postValue(AppState.Delete)
    }

    fun error(){
        blankLiveData.postValue(AppState.Error)
    }

    fun okButtunPress(){
        blankLiveData.postValue(AppState.Success)
    }
}