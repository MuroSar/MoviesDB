package com.sartimau.data

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRequestGenerator {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor { chain ->
            val defaultRequest = chain.request()

            val defaultHttpUrl = defaultRequest.url()

            val httpUrl = defaultHttpUrl.newBuilder().build()

            val requestBuilder = defaultRequest.newBuilder()
                .url(httpUrl)

            chain.proceed(requestBuilder.build())
        }
        .addInterceptor { chain ->
            val request = chain.request()
            var response = chain.proceed(request)
            var tryOuts = INIT_TRYOUT

            while (!response.isSuccessful && tryOuts < MAX_TRYOUTS) {
                Log.d(
                    this@MoviesRequestGenerator.javaClass.simpleName, "intercept: timeout/connection failure, " +
                            "performing automatic retry ${(tryOuts + 1)}"
                )
                tryOuts++
                response = chain.proceed(request)
            }

            response
        }.addNetworkInterceptor(StethoInterceptor())

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }

    companion object {
        const val API_VERSION = "3"
        const val BASE_URL = "https://api.themoviedb.org/$API_VERSION/"
        const val BASE_MOVIE_URL = "https://image.tmdb.org/t/p/w500/"

        const val API_KEY_V3 = "64b238747d756b36c54245b49ae15afd"
        const val API_KEY_V4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2NGIyMzg3NDdkNzU2YjM2YzU0MjQ1YjQ5YWUxNWFmZCIsInN1YiI6IjVkZDFlMjdh" +
                "ZmQ2ZmExMDAxMjgwMDQzNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._pCVHHGwuR2hDlguJ8Gph6lITx65cQNPMmzJOO6LksA"


        const val MAX_TRYOUTS = 3
        const val INIT_TRYOUT = 1
    }
}
