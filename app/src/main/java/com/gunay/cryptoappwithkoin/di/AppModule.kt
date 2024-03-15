package com.gunay.cryptoappwithkoin.di

import com.gunay.cryptoappwithkoin.repo.CryptoDownload
import com.gunay.cryptoappwithkoin.repo.CryptoDownloadImpl
import com.gunay.cryptoappwithkoin.service.CryptoAPI
import com.gunay.cryptoappwithkoin.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    //Singleton Scope
    single{
        val BASE_URL = "https://raw.githubusercontent.com/"

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)
    }


    //bu interfaceden bir injection yapıldığı zaman bunu enjekte eder
    //constructor işlemleri get() ile otomatik olarak halledilir CryptoDownloadImpl() sınıfının const una bakar oto halleder
    //ama enjecte edilecek şeyin hem gerekli const içinde verilmesi hemde module içinde nasıl inject olacağının verilmesi lazım
    single<CryptoDownload> {
        CryptoDownloadImpl(get())
    }

    //Koin ile viewModel inject edilebiliyor
    viewModel {
        CryptoViewModel(get())
    }

    //her inject edildiğinde yeni bir instance oluşturur
    factory {

    }

}