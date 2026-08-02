package com.devlaunch.desktop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform