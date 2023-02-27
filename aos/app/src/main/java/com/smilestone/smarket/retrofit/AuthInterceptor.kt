package com.smilestone.smarket.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val auth: String?): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().addHeader("Authorization", auth!!).build()
        return chain.proceed(req)
    }

}