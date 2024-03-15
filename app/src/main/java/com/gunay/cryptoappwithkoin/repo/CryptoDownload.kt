package com.gunay.cryptoappwithkoin.repo

import com.gunay.cryptoappwithkoin.model.CryptoModel
import com.gunay.cryptoappwithkoin.util.Resource

interface CryptoDownload {

    suspend fun downloadCryptos() : Resource<List<CryptoModel>>

}