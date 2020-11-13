package com.itcuandroid.rickandmorty.viewmodels

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.models.Episode
import com.itcuandroid.rickandmorty.models.Results
import com.itcuandroid.rickandmorty.respositories.RickAndMortyRepository
import kotlinx.coroutines.launch

class InfoEpisodeActivityViewModel(application: Application): AndroidViewModel(application) {

    private val rickAndMortyRepository = RickAndMortyRepository();
    val episodeInfoLiveData = MutableLiveData<Episode>()
    val characterLiveData = MutableLiveData<List<Results>>()

    fun getEpisodeData(urlEpisode: String){
        viewModelScope.launch {

            var episode = rickAndMortyRepository.getEpisodesByUrl(urlEpisode);
            episodeInfoLiveData.postValue(episode);
        }
    }

    fun getCharactersEpisode(characters: List<Int>){
        viewModelScope.launch {

            var charactersId= TextUtils.join(",",characters);
            var characters = rickAndMortyRepository.getCharactersById(charactersId);

            characterLiveData.postValue(characters);
        }
    }

}