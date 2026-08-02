package com.devlaunch.desktop.core.config

import java.io.File
import java.util.Properties

actual object Config {
    private val properties = Properties().apply {
        println("--- DevLaunch Config Loader Init ---")
        val userDir = System.getProperty("user.dir")
        println("Current user.dir: $userDir")
        
        val paths = listOf(
            ".env",
            "DevLaunchDesktop/.env",
            "../.env",
            "../../.env",
            "desktopApp/.env",
            "../DevLaunchDesktop/.env",
            userDir?.let { "$it/.env" },
            userDir?.let { "$it/DevLaunchDesktop/.env" },
            userDir?.let { "$it/../.env" }
        )
        
        var envFile: File? = null
        for (path in paths) {
            if (path == null) continue
            val file = File(path)
            println("Checking .env at path: $path (Absolute: ${file.absolutePath}) -> exists = ${file.exists()}")
            if (file.exists() && file.isFile) {
                envFile = file
                println("Found .env file at: ${file.absolutePath}")
                break
            }
        }
        
        if (envFile != null) {
            try {
                envFile.inputStream().use { load(it) }
                println("Successfully loaded .env properties.")
            } catch (e: Exception) {
                println("Error reading .env file: ${e.message}")
            }
        } else {
            println("WARNING: .env file not found in checked paths.")
        }
    }

    actual val supabaseUrl: String = (properties.getProperty("SUPABASE_URL") ?: System.getenv("SUPABASE_URL") ?: "").trim()
    actual val supabaseKey: String = (properties.getProperty("SUPABASE_KEY") ?: System.getenv("SUPABASE_KEY") ?: "").trim()

    init {
        println("Config.supabaseUrl: '$supabaseUrl'")
        println("Config.supabaseKey length: ${supabaseKey.length}")
        println("-------------------------------------")
    }

    private val systemUser = System.getProperty("user.name") ?: "developer"
    private val defaultName = systemUser.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    private val defaultEmail = "${systemUser.lowercase()}@devlaunch.com"

    actual val mockGoogleEmail: String = (properties.getProperty("MOCK_GOOGLE_EMAIL") ?: defaultEmail).trim()
    actual val mockGoogleName: String = (properties.getProperty("MOCK_GOOGLE_NAME") ?: defaultName).trim()
}
