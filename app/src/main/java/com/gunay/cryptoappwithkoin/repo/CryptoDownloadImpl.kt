package com.gunay.cryptoappwithkoin.repo

import com.gunay.cryptoappwithkoin.model.CryptoModel
import com.gunay.cryptoappwithkoin.service.CryptoAPI
import com.gunay.cryptoappwithkoin.util.Resource

class CryptoDownloadImpl(private val api: CryptoAPI) : CryptoDownload {

    override suspend fun downloadCryptos(): Resource<List<CryptoModel>> {

        return try {
            val response = api.getData()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            }else{
                Resource.error("Error", null)
            }
        } catch (e : Exception){
            Resource.error("No Data", null)
        }
    }

}