package com.devlaunch.desktop.core.config

expect object Config {
    val supabaseUrl: String
    val supabaseKey: String
    val mockGoogleEmail: String
    val mockGoogleName: String
}
