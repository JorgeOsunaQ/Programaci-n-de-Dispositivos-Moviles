package com.itcuandroid.rickandmorty.net

import com.itcuandroid.rickandmorty.BASE_URL
import com.itcuandroid.rickandmorty.net.services.RickAndMortyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        @JvmStatic
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @JvmStatic
        public val rickAndMortService = retrofit.create(RickAndMortyService::class.java)
    }
}