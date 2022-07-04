package karpenko.diploma.mycryptoapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiFactory {

    private const val URL = "https://min-api.cryptocompare.com/data/"
    const val IMAGE_URL = "https://cryptocompare.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(URL)
        .build()

    val apiService: ApiService = retrofit.create(ApiService:: class.java)
}