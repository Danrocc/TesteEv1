package com.example.testeev1.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeev1.GuestModel
import com.example.testeev1.repository.GuestRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    private val listHome = MutableLiveData<List<GuestModel>>()
    val home: LiveData<List<GuestModel>> = listHome

    fun home(){
       listHome.value = repository.getAll()
    }
    fun getAbsent(){
        listHome.value = repository.getAbsent()
    }

    fun getPresent(){
        listHome.value = repository.getPresent()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}