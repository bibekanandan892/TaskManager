package com.bibek.taskmanager.di

import com.bibek.taskmanager.domain.repository.AppDataStore
import com.bibek.taskmanager.utils.DefaultDispatcherProvider
import com.bibek.taskmanager.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Singleton
/**
 * Author: Bibekananda Nayak
 * Date: 21-02-2024
 * Description: This module provides a singleton instance of HttpClient for making network requests.
 * It utilizes an AppDataStore to retrieve the access token asynchronously and injects it into the
 * request headers for authorization. It also configures the HttpClient with OkHttp as the engine,
 * sets up content negotiation for JSON and form URL-encoded data, and configures timeouts and logging.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton instance of HttpClient for making network requests.
     * @param appDataStore The AppDataStore used to retrieve the access token.
     * @return An instance of HttpClient configured with necessary settings.
     */
    @Singleton
    @Provides
    fun provideHttpClient(appDataStore: AppDataStore): HttpClient {
        // MutableStateFlow to hold the access token
        val tokenFlow : MutableStateFlow<String> = MutableStateFlow("")

        // Asynchronously collect the access token from AppDataStore
        CoroutineScope(Dispatchers.IO).launch {
            appDataStore.getAccessToken().collectLatest {
                tokenFlow.value = "Bearer $it"
            }
        }

        // Configure and return HttpClient instance
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json)
                json(contentType = ContentType.Application.FormUrlEncoded)
            }
            expectSuccess = true

            install(HttpTimeout) {
                socketTimeoutMillis = 60000
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                // Inject the access token into request headers for authorization
                header(key = "Authorization", value = tokenFlow.value)
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()
}
