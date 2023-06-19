package com.chockydevelopment.wallpaperapp.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor : Interceptor {

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logger.intercept(chain)
        return chain.proceed(request)
    }
}