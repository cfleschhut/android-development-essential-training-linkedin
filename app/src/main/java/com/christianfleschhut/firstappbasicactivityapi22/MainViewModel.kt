package com.christianfleschhut.firstappbasicactivityapi22

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val LOG_TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val _info: MutableLiveData<Int> = MutableLiveData()
    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    private val _totalAmount: MutableLiveData<Int> = MutableLiveData()

    val info: LiveData<Int> = _info
    val quantity: LiveData<Int> = _quantity
    val totalAmount: LiveData<Int> = _totalAmount

    init {
        Log.i(LOG_TAG, "created")
        _info.value = 0
    }

    fun loadData() {
        _info.value = _info.value!! + 1
    }

    fun increaseQuantity() {
        _quantity.value = _quantity.value!! + 1
    }

    fun decreaseQuantity() {
        _quantity.value = _quantity.value!! - 1
    }

    fun checkout() {
        _totalAmount.value = _quantity.value!! * 5
    }
}