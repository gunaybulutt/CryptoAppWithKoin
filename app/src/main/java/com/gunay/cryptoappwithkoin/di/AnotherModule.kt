package com.gunay.cryptoappwithkoin.di

import com.gunay.cryptoappwithkoin.view.ListFragment
import org.koin.core.qualifier.named
import org.koin.dsl.module

val anotherModule = module{

    //sadece ListFragment içinde yaşayan bir enjeksiyon
    //sadece ListFragment içinde yapılacak inject'ler için kullanılır
    scope<ListFragment>{
        //sadece bu scope'de olan bir inject
        scoped(qualifier = named("hello")){
            "Hello Kotlin" //string inject
        }

        scoped(qualifier = named("hi")) {
            "Hi Kotlin"
        }
    }

}