package com.itcuandroid.rickandmorty.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.respositories.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val rickAndMortyRepository = RickAndMortyRepository();
    val rickAndMortyLiveData = MutableLiveData<List<CharacterView>>()
    private val rickAndMortyList =  mutableListOf<CharacterView>()
    private var page = 0;
    private var isLoadingCharacters = false
    var hasNextCharacters = true

    fun getCharacters(){

        if (!isLoadingCharacters && hasNextCharacters) {
            isLoadingCharacters = true;
            page++;
            viewModelScope.launch {
                val characterPageRequest = rickAndMortyRepository.getPage(page);

                rickAndMortyList.addAll(
                    characterPageRequest.results.map { result ->

                        var firsEpisodeSeen =
                            rickAndMortyRepository.getEpisodesByUrl(result.episode.first());

                        CharacterView(
                            result.image,
                            result.name,
                            result.status,
                            result.species,
                            result.location.name,
                            firsEpisodeSeen.name,
                            firsEpisodeSeen.url
                        )
                    }
                )

                rickAndMortyLiveData.postValue(rickAndMortyList);


                hasNextCharacters = characterPageRequest.info.next.isNotEmpty()
                isLoadingCharacters = false
            }
        }
    }

}