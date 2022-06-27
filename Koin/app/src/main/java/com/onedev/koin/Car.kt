package com.onedev.koin

class Car(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}