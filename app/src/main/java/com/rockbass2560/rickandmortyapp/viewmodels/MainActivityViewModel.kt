package com.rockbass2560.rickandmortyapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rockbass2560.rickandmortyapp.models.CharacterView
import com.rockbass2560.rickandmortyapp.repositories.RickAndMortyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val rickAndMortyRepository = RickAndMortyRepository();
    val rickAndMortyListLiveData = MutableLiveData<List<CharacterView>>()
    private val rickAndMortyList = mutableListOf<CharacterView>()
    private var page = 0;

    fun getCharacters() {
        page++;
        viewModelScope.launch {
            val characterPageRequest = rickAndMortyRepository.getPage(page)
            rickAndMortyList.addAll(
                characterPageRequest.results.map { result ->
                    CharacterView(
                        result.image,
                        result.name,
                        result.status,
                        result.species,
                        result.location.name,
                        rickAndMortyRepository.getEpisodeByUrl(result.episode.first()).name
                    )
                }
            )

            rickAndMortyListLiveData.postValue(rickAndMortyList)
        }
    }

}