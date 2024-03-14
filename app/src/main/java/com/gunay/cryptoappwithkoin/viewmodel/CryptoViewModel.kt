package com.gunay.cryptoappwithkoin.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunay.cryptoappwithkoin.model.CryptoModel
import com.gunay.cryptoappwithkoin.service.CryptoAPI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoViewModel : ViewModel() {

    val cryptoList = MutableLiveData<List<CryptoModel>>()
    val cryptoEror = MutableLiveData<Boolean>()
    val cryptoLoading = MutableLiveData<Boolean>()


    fun getDataFromAPI() {
        cryptoLoading.value = true

        val BASE_URL = "https://raw.githubusercontent.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = retrofit.getData()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    cryptoEror.value = false
                    cryptoLoading.value = false

                    response.body()?.let {
                        cryptoList.value = it
                    }
                }
            }
        }
    }


    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error : ${throwable.localizedMessage}")
        cryptoEror.value = true
    }

}