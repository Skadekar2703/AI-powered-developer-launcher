package com.devlaunch.desktop.feature.auth.services

import com.devlaunch.desktop.feature.auth.model.User
import com.devlaunch.desktop.core.config.Config
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.URI
import java.awt.Desktop
import java.io.File
import java.io.FileOutputStream
import java.util.Properties

class SupabaseAuthService : AuthService {
    private var currentUser: User? = null

    private val sessionFile: File
        get() {
            val userHome = System.getProperty("user.home")
            val dir = File(userHome, ".devlaunch")
            if (!dir.exists()) {
                dir.mkdirs()
            }
            return File(dir, "session.properties")
        }

    private fun saveSession(accessToken: String, refreshToken: String) {
        try {
            val props = Properties()
            props.setProperty("access_token", accessToken)
            props.setProperty("refresh_token", refreshToken)
            FileOutputStream(sessionFile).use { props.store(it, "DevLaunch Session") }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearSession() {
        try {
            if (sessionFile.exists()) {
                sessionFile.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        if (Config.supabaseUrl.isBlank() || Config.supabaseKey.isBlank()) {
            return Result.failure(Exception("Supabase config is empty. Please configure your .env file."))
        }

        try {
            val client = HttpClient.newHttpClient()
            val body = """{"email":"$email","password":"$password"}"""
            val request = HttpRequest.newBuilder()
                .uri(URI.create("${Config.supabaseUrl}/auth/v1/token?grant_type=password"))
                .header("Content-Type", "application/json")
                .header("apikey", Config.supabaseKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            val responseBody = response.body()

            if (response.statusCode() == 200) {
                val accessToken = extractJsonField(responseBody, "access_token")
                val refreshToken = extractJsonField(responseBody, "refresh_token")
                if (accessToken != null && refreshToken != null) {
                    saveSession(accessToken, refreshToken)
                }

                val user = parseUserFromJson(responseBody)
                currentUser = user
                return Result.success(user)
            } else {
                val errorMsg = extractJsonField(responseBody, "error_description") 
                    ?: extractJsonField(responseBody, "msg") 
                    ?: "Authentication failed (${response.statusCode()})"
                return Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun signup(email: String, password: String): Result<User> {
        if (Config.supabaseUrl.isBlank() || Config.supabaseKey.isBlank()) {
            return Result.failure(Exception("Supabase config is empty. Please configure your .env file."))
        }

        try {
            val client = HttpClient.newHttpClient()
            val body = """{"email":"$email","password":"$password"}"""
            val request = HttpRequest.newBuilder()
                .uri(URI.create("${Config.supabaseUrl}/auth/v1/signup"))
                .header("Content-Type", "application/json")
                .header("apikey", Config.supabaseKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            val responseBody = response.body()

            if (response.statusCode() == 200) {
                val user = parseUserFromJson(responseBody)
                // If auto-confirm is on, we might have tokens immediately
                val accessToken = extractJsonField(responseBody, "access_token")
                val refreshToken = extractJsonField(responseBody, "refresh_token")
                if (accessToken != null && refreshToken != null) {
                    saveSession(accessToken, refreshToken)
                    currentUser = user
                } else {
                    // Requires email verification
                    return Result.failure(Exception("Confirmation email sent. Please verify your email before logging in."))
                }
                return Result.success(user)
            } else {
                val errorMsg = extractJsonField(responseBody, "error_description") 
                    ?: extractJsonField(responseBody, "msg") 
                    ?: "Registration failed (${response.statusCode()})"
                return Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun loginWithGoogle(email: String?, name: String?): Result<User> {
        if (Config.supabaseUrl.isBlank() || Config.supabaseKey.isBlank()) {
            return Result.failure(Exception("Supabase config is empty. Please configure your .env file."))
        }

        val port = 5732
        val redirectUri = "http://localhost:$port/callback"
        val authUrl = "${Config.supabaseUrl}/auth/v1/authorize?provider=google&redirect_to=$redirectUri"

        return suspendCancellableCoroutine { continuation ->
            var server: HttpServer? = null
            try {
                server = HttpServer.create(InetSocketAddress(port), 0)
                
                server.createContext("/callback") { exchange ->
                    val html = """
                        <!DOCTYPE html>
                        <html>
                        <head>
                            <title>Authenticating...</title>
                            <style>
                                body { background-color: #09090B; color: #F4F4F5; font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
                                .card { text-align: center; border: 1px solid #27272A; background-color: #18181B; padding: 40px; border-radius: 12px; max-width: 400px; box-shadow: 0 4px 12px rgba(0,0,0,0.5); }
                                h2 { color: #8B5CF6; margin: 0 0 16px 0; }
                                p { color: #A1A1AA; font-size: 14px; line-height: 1.5; }
                            </style>
                        </head>
                        <body>
                            <div class="card">
                                <h2>Connecting...</h2>
                                <p>Passing authorization credentials to DevLaunch Desktop.</p>
                            </div>
                            <script>
                                var hash = window.location.hash;
                                if (hash && hash.startsWith('#')) {
                                    window.location.href = '/token?' + hash.substring(1);
                                } else {
                                    document.body.innerHTML = '<div class="card"><h2 style="color:#EF4444">Failed</h2><p>No tokens detected in authorization URL.</p></div>';
                                }
                            </script>
                        </body>
                        </html>
                    """.trimIndent()
                    
                    val bytes = html.toByteArray()
                    exchange.responseHeaders.set("Content-Type", "text/html; charset=utf-8")
                    exchange.sendResponseHeaders(200, bytes.size.toLong())
                    exchange.responseBody.use { it.write(bytes) }
                }

                server.createContext("/token") { exchange ->
                    val query = exchange.requestURI.query
                    val params = parseQuery(query)
                    val accessToken = params["access_token"]
                    val refreshToken = params["refresh_token"]

                    val successHtml = if (accessToken != null && refreshToken != null) {
                        saveSession(accessToken, refreshToken)
                        
                        val userResult = fetchUser(accessToken)
                        if (userResult.isSuccess) {
                            currentUser = userResult.getOrNull()
                            if (continuation.isActive) {
                                continuation.resume(Result.success(currentUser!!))
                            }
                        } else {
                            if (continuation.isActive) {
                                continuation.resume(Result.failure(userResult.exceptionOrNull() ?: Exception("Failed to fetch user profile.")))
                            }
                        }

                        """
                            <!DOCTYPE html>
                            <html>
                            <head>
                                <title>Sign In Complete</title>
                                <style>
                                    body { background-color: #09090B; color: #F4F4F5; font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
                                    .card { text-align: center; border: 1px solid #27272A; background-color: #18181B; padding: 40px; border-radius: 12px; max-width: 400px; box-shadow: 0 4px 12px rgba(0,0,0,0.5); }
                                    h2 { color: #8B5CF6; margin: 0 0 16px 0; }
                                    p { color: #A1A1AA; font-size: 14px; line-height: 1.5; }
                                </style>
                            </head>
                            <body>
                                <div class="card">
                                    <h2>Authenticated Successfully!</h2>
                                    <p>You have signed in to DevLaunch Desktop.</p>
                                    <p style="font-size: 12px; margin-top: 16px;">You can now close this tab and return to the application.</p>
                                </div>
                            </body>
                            </html>
                        """.trimIndent()
                    } else {
                        if (continuation.isActive) {
                            continuation.resume(Result.failure(Exception("Failed to capture token parameters from query redirect.")))
                        }
                        """
                            <!DOCTYPE html>
                            <html>
                            <head>
                                <title>Sign In Failed</title>
                                <style>
                                    body { background-color: #09090B; color: #F4F4F5; font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
                                    .card { text-align: center; border: 1px solid #27272A; background-color: #18181B; padding: 40px; border-radius: 12px; }
                                    h2 { color: #EF4444; margin: 0 0 16px 0; }
                                    p { color: #A1A1AA; font-size: 14px; }
                                </style>
                            </head>
                            <body>
                                <div class="card">
                                    <h2>Authentication Failed</h2>
                                    <p>Failed to capture valid session tokens from the redirect callback.</p>
                                </div>
                            </body>
                            </html>
                        """.trimIndent()
                    }

                    val bytes = successHtml.toByteArray()
                    exchange.responseHeaders.set("Content-Type", "text/html; charset=utf-8")
                    exchange.sendResponseHeaders(200, bytes.size.toLong())
                    exchange.responseBody.use { it.write(bytes) }

                    Thread {
                        Thread.sleep(1000)
                        server?.stop(0)
                    }.start()
                }

                server.start()

                openBrowser(authUrl)

                continuation.invokeOnCancellation {
                    try {
                        server?.stop(0)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            } catch (e: Exception) {
                try {
                    server?.stop(0)
                } catch (ex: Exception) {}
                if (continuation.isActive) {
                    continuation.resume(Result.failure(e))
                }
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        clearSession()
        currentUser = null
        return Result.success(Unit)
    }

    override suspend fun checkSession(): Result<User?> {
        val file = sessionFile
        if (!file.exists()) return Result.success(null)

        val props = Properties()
        try {
            file.inputStream().use { props.load(it) }
        } catch (e: Exception) {
            return Result.success(null)
        }

        val accessToken = props.getProperty("access_token") ?: return Result.success(null)
        val refreshToken = props.getProperty("refresh_token") ?: return Result.success(null)

        val userResult = fetchUser(accessToken)
        if (userResult.isSuccess) {
            currentUser = userResult.getOrNull()
            return Result.success(currentUser)
        }

        val newSession = refreshSession(refreshToken)
        if (newSession != null) {
            saveSession(newSession.first, newSession.second)
            val refreshedUserResult = fetchUser(newSession.first)
            if (refreshedUserResult.isSuccess) {
                currentUser = refreshedUserResult.getOrNull()
                return Result.success(currentUser)
            }
        }

        clearSession()
        return Result.success(null)
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        if (Config.supabaseUrl.isBlank() || Config.supabaseKey.isBlank()) {
            return Result.failure(Exception("Supabase config is empty. Please configure your .env file."))
        }

        try {
            val client = HttpClient.newHttpClient()
            val body = """{"email":"$email"}"""
            val request = HttpRequest.newBuilder()
                .uri(URI.create("${Config.supabaseUrl}/auth/v1/recover"))
                .header("Content-Type", "application/json")
                .header("apikey", Config.supabaseKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                return Result.success(Unit)
            } else {
                val responseBody = response.body()
                val errorMsg = extractJsonField(responseBody, "error_description") 
                    ?: extractJsonField(responseBody, "msg") 
                    ?: "Failed to send reset link (${response.statusCode()})"
                return Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun openBrowser(url: String) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(URI(url))
        } else {
            val os = System.getProperty("os.name").lowercase()
            val runtime = Runtime.getRuntime()
            when {
                os.contains("win") -> runtime.exec(arrayOf("rundll32", "url.dll,FileProtocolHandler", url))
                os.contains("mac") -> runtime.exec(arrayOf("open", url))
                else -> runtime.exec(arrayOf("xdg-open", url))
            }
        }
    }

    private fun fetchUser(accessToken: String): Result<User> {
        try {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("${Config.supabaseUrl}/auth/v1/user"))
                .header("Authorization", "Bearer $accessToken")
                .header("apikey", Config.supabaseKey)
                .GET()
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                val user = parseUserFromJson(response.body())
                return Result.success(user)
            } else {
                return Result.failure(Exception("HTTP ${response.statusCode()}: failed to verify user details."))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun refreshSession(refreshToken: String): Pair<String, String>? {
        try {
            val client = HttpClient.newHttpClient()
            val body = """{"refresh_token":"$refreshToken"}"""
            val request = HttpRequest.newBuilder()
                .uri(URI.create("${Config.supabaseUrl}/auth/v1/token?grant_type=refresh_token"))
                .header("Content-Type", "application/json")
                .header("apikey", Config.supabaseKey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                val json = response.body()
                val newAccess = extractJsonField(json, "access_token")
                val newRefresh = extractJsonField(json, "refresh_token")
                if (newAccess != null && newRefresh != null) {
                    return Pair(newAccess, newRefresh)
                }
            }
        } catch (e: Exception) {}
        return null
    }

    private fun parseUserFromJson(json: String): User {
        val email = extractJsonField(json, "email") ?: "unknown@devlaunch.com"
        val name = extractJsonField(json, "full_name") 
            ?: extractJsonField(json, "name") 
            ?: email.substringBefore("@").replaceFirstChar { it.uppercase() }
        return User(email = email, name = name)
    }

    private fun extractJsonField(json: String, field: String): String? {
        val regex = "\"$field\"\\s*:\\s*\"([^\"]+)\"".toRegex()
        return regex.find(json)?.groupValues?.get(1)
    }

    private fun parseQuery(query: String?): Map<String, String> {
        val result = mutableMapOf<String, String>()
        if (query == null) return result
        val pairs = query.split("&")
        for (pair in pairs) {
            val idx = pair.indexOf("=")
            if (idx > 0) {
                val key = pair.substring(0, idx)
                val value = pair.substring(idx + 1)
                result[key] = value
            }
        }
        return result
    }
}
