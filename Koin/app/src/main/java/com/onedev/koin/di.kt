package com.onedev.koin

import org.koin.dsl.module

val appModule = module {
    factory {
        Engine()
    }
    single {
        Car(get())
    }
}