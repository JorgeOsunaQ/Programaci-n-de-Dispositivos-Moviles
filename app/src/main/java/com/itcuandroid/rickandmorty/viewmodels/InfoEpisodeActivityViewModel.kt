package com.itcuandroid.rickandmorty.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.models.Episode
import com.itcuandroid.rickandmorty.respositories.RickAndMortyRepository
import kotlinx.coroutines.launch

class InfoEpisodeActivityViewModel(application: Application): AndroidViewModel(application) {

    private val rickAndMortyRepository = RickAndMortyRepository();
    private val characterList =  mutableListOf<CharacterView>()
    val episodeInfoLiveData = MutableLiveData<Episode>()

    fun getEpisodeData(urlEpisode: String){
        viewModelScope.launch {

            var episode = rickAndMortyRepository.getEpisodesByUrl(urlEpisode);
            episodeInfoLiveData.postValue(episode);
        }
    }
}