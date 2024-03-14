package com.gunay.cryptoappwithkoin.service

import com.gunay.cryptoappwithkoin.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    //https://raw.githubusercontent.com/ -- atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    suspend fun getData() : Response<List<CryptoModel>>

}