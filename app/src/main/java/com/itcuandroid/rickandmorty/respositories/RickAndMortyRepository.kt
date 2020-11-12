package com.itcuandroid.rickandmorty.respositories

import com.itcuandroid.rickandmorty.models.CharacterPageRequest
import com.itcuandroid.rickandmorty.models.Episode
import com.itcuandroid.rickandmorty.net.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RickAndMortyRepository {

    suspend fun getPage(page: Int): CharacterPageRequest {
        return suspendCoroutine { continuation ->
            RetrofitInstance.rickAndMortService.getCharacterPage(page).enqueue(object: Callback<CharacterPageRequest>{

                override fun onResponse(
                    call: Call<CharacterPageRequest>,
                    response: Response<CharacterPageRequest>
                ) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }
                    else{
                        continuation.resumeWithException(Exception("La respuesta no fue exitosa"))
                    }
                }

                override fun onFailure(call: Call<CharacterPageRequest>, t: Throwable) {
                    continuation.resumeWithException(t);
                }

            })
        }
    }

    suspend fun getEpisodesByUrl(url: String)= suspendCoroutine<Episode> {
        RetrofitInstance.rickAndMortService.getEpisodeByUrl(url).enqueue(
            object: Callback<Episode>{
                override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                    it.resume(response.body()!!)
                }

                override fun onFailure(call: Call<Episode>, t: Throwable) {
                    it.resumeWithException(t);
                }

            }
        )
    }

}