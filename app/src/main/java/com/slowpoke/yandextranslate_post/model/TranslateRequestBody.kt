package com.slowpoke.yandextranslate_post.model

data class TranslateRequestBody(
    val folderId: String,
    val texts: List<String>,
    val targetLanguageCode: String
)