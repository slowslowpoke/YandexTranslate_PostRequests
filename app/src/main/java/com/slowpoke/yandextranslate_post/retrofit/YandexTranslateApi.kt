package com.slowpoke.yandextranslate_post.retrofit

import com.slowpoke.yandextranslate_post.model.TranslateRequestBody
import com.slowpoke.yandextranslate_post.model.response.TranslationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val API_KEY = "AQVNzQVsdvDvlp8Sm71uWLQZwIdHBjShQo6p6PGE"

interface YandexTranslateApi {
    @Headers("Content-Type: application/json",
        "Authorization: Api-Key $API_KEY")
    @POST("translate/v2/translate")
    suspend fun getWordTranslation(@Body requestedWord: TranslateRequestBody): Response<TranslationResponse>
}