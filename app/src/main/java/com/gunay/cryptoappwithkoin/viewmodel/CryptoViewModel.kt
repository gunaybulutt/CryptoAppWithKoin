package com.gunay.cryptoappwithkoin.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunay.cryptoappwithkoin.model.CryptoModel
import com.gunay.cryptoappwithkoin.repo.CryptoDownload
import com.gunay.cryptoappwithkoin.util.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CryptoViewModel(private val cryptoDownloadRepository : CryptoDownload) : ViewModel() {

    val cryptoList = MutableLiveData<Resource<List<CryptoModel>>>()
    val cryptoEror = MutableLiveData<Resource<Boolean>>()
    val cryptoLoading = MutableLiveData<Resource<Boolean>>()


    fun getDataFromAPI() {
        cryptoLoading.value = Resource.loading(data = true)


        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val resource = cryptoDownloadRepository.downloadCryptos()
            withContext(Dispatchers.Main) {
                resource.data?.let {
                    cryptoList.value = resource
                    cryptoLoading.value = Resource.loading(data = false)
                    cryptoEror.value = Resource.error("", data = false)
                }


            }

        }
    }


    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error : ${throwable.localizedMessage}")
        cryptoEror.value = Resource.error(throwable.localizedMessage ?: "error", data = true)
    }

}