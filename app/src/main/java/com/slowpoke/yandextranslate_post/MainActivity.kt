package com.slowpoke.yandextranslate_post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.slowpoke.yandextranslate_post.model.TranslateRequestBody
import com.slowpoke.yandextranslate_post.model.response.TranslationResponse
import com.slowpoke.yandextranslate_post.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "YANDEX_API"
const val FOLDER_ID = "b1gpne4qo6st3l5euhsh"
const val TARGET_LANGUAGE_CODE = "kk"

class MainActivity : AppCompatActivity() {
    private lateinit var tvTranslation: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTranslation = findViewById<TextView>(R.id.tvTranslation)
        val etSearchWord = findViewById<EditText>(R.id.etSearchWord)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        btnSearch.setOnClickListener {
            val requestBody = TranslateRequestBody(
                FOLDER_ID,
                listOf(etSearchWord.text.toString()),
                TARGET_LANGUAGE_CODE
            )

            lifecycleScope.launch {
                progressBar.isVisible = true
                val response = try {
                    RetrofitInstance.api.getWordTranslation(requestBody)
                } catch (e: IOException) {
                    Log.e(TAG, "IOException, check your internet connection")
                    progressBar.isVisible = false
                    return@launch
                } catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    progressBar.isVisible = false
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    bindViewsWithTranslation(response.body()!!)
                } else {
                    Log.e(TAG, "Response unsuccessful")
                }
                progressBar.isVisible = false


            }
        }

    }

    private fun bindViewsWithTranslation(translation: TranslationResponse) {
        tvTranslation.text = "Перевод слова: ${translation.translations[0].text}"

    }


}