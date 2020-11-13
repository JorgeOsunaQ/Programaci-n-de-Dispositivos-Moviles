package com.itcuandroid.rickandmorty.net.services

import com.itcuandroid.rickandmorty.models.CharacterPageRequest
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.models.Episode
import com.itcuandroid.rickandmorty.models.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyService {

    @GET("character")
    fun getCharacterPage(@Query("page") page: Int) : Call<CharacterPageRequest>

    @GET
    fun getEpisodeByUrl(@Url url: String) : Call<Episode>

    @GET("character/{characters}")
    fun getCharacterById(@Path(value="characters") characters: String) : Call<List<Results>>
}