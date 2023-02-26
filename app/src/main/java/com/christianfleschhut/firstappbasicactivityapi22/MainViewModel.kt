package com.christianfleschhut.firstappbasicactivityapi22

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.christianfleschhut.firstappbasicactivityapi22.data.ProductRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _info: MutableLiveData<Int> = MutableLiveData(0)
    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    private val _totalAmount: MutableLiveData<Int> = MutableLiveData()

    val info: LiveData<Int> = _info
    val quantity: LiveData<Int> = _quantity
    val totalAmount: LiveData<Int> = _totalAmount

    private var productRepository: ProductRepository = ProductRepository()

    init {
        Log.i("MainViewModel", "created")

        val data = productRepository.getProducts(app, "olive_oils_data.json")
        data?.forEach {
            Log.i("JSON data", "Product: ${it.name}")
        }
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