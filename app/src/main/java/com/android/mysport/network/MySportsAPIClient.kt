package com.android.mysport.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The API interface client to get the retrofit instance
 */
object MySportsAPIClient {
    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
    private var retrofit: Retrofit? = null

    /**
     * Singleton design pattern is used to build Retrofit's instance
     */
    @JvmStatic
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}