package com.slowpoke.yandextranslate_post.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: YandexTranslateApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://translate.api.cloud.yandex.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YandexTranslateApi::class.java)
    }
}

