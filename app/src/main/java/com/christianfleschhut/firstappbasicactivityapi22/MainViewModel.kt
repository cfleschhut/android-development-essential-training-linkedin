package com.christianfleschhut.firstappbasicactivityapi22

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.christianfleschhut.firstappbasicactivityapi22.data.ProductRepository
import com.christianfleschhut.firstappbasicactivityapi22.data.Stock
import com.christianfleschhut.firstappbasicactivityapi22.data.StockRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val _info: MutableLiveData<Int> = MutableLiveData(0)
    private val _quantity: MutableLiveData<Int> = MutableLiveData(0)
    private val _totalAmount: MutableLiveData<Int> = MutableLiveData()
    private val _stockInfo: MutableLiveData<Stock> = MutableLiveData()

    val info: LiveData<Int> = _info
    val quantity: LiveData<Int> = _quantity
    val totalAmount: LiveData<Int> = _totalAmount
    val stockInfo: LiveData<Stock> = _stockInfo

    private var productRepository: ProductRepository = ProductRepository()
    private val stockRepository: StockRepository = StockRepository(app)

    init {
        Log.i("MainViewModel", "created")

        val productData = productRepository.getProducts(app, "olive_oils_data.json")
        productData?.forEach {
            Log.i("JSON data", "Product: ${it.name}")
        }
    }

    fun getStockData(firmId: Int) {
        val stock = stockRepository.getStockDataForFirm(firmId)
        stock?.let {
            _stockInfo.value = it
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