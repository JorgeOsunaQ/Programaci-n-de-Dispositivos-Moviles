package com.rockbass2560.rickandmortyapp.repositories

import com.rockbass2560.rickandmortyapp.models.CharacterPageRequest
import com.rockbass2560.rickandmortyapp.net.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickAndMortyRepository {

    fun getPage(page: Int) {
        RetrofitInstance.rickAndMortyService.getCharacterPage(page).enqueue(object:
            Callback<CharacterPageRequest> {
            override fun onResponse(
                call: Call<CharacterPageRequest>,
                response: Response<CharacterPageRequest>
            ) {

            }

            override fun onFailure(call: Call<CharacterPageRequest>, t: Throwable) {
                
            }

        })
    }

}