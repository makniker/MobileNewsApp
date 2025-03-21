package com.makniker.itmoapp.data.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    //Я никогда не буду так делать в реальном приложении,
    // но так как время на олимпиаде ограничено, выбрал такое решение.
    //Апи ключ можно положить в преференс сторадж или сокрыть с помощью билдконфига
    private val API_KEY = "05a8329a928e46d989154cf1db10aa7c"
    override fun intercept(chain: Interceptor.Chain): Response {
        val myUrl = chain.request().url.newBuilder().addQueryParameter("apiKey",API_KEY).build()
        val newRequest = chain.request().newBuilder().url(myUrl).build()
        return chain.proceed(newRequest)
    }
}