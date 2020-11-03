package com.rockbass2560.rickandmortyapp.net.services

import com.rockbass2560.rickandmortyapp.models.CharacterPageRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character")
    fun getCharacterPage(@Query("page") page: Int) : Call<CharacterPageRequest>
}