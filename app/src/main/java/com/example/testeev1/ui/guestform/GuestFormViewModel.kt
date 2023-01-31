package com.example.testeev1.ui.guestform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeev1.GuestModel
import com.example.testeev1.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveguest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Inserção com sucesso"
            } else {
                _saveGuest.value = "Falhou!"
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Atualização com sucesso"
            } else {
                _saveGuest.value = "Falhou!"
            }
        }

    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}