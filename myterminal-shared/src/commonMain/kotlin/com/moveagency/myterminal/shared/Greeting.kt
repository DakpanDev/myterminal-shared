package com.moveagency.myterminal.shared

class Greeting {
    private val platform = getPlatform()

    fun greet() = "Hello, ${platform.name}"
}
