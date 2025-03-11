package com.moveagency.myterminal.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
