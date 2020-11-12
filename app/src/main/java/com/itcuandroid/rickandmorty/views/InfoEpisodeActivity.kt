package com.itcuandroid.rickandmorty.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.itcuandroid.rickandmorty.R
import com.itcuandroid.rickandmorty.databinding.ActivityInfoEpisodeBinding
import com.itcuandroid.rickandmorty.models.Episode
import com.itcuandroid.rickandmorty.viewmodels.InfoEpisodeActivityViewModel

class InfoEpisodeActivity : AppCompatActivity() {

    private val infoEpisodeActivityViewModel : InfoEpisodeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_episode)

        val urlEpisodio = intent.getStringExtra("Episode");
        Log.d("UrlEpisodio", urlEpisodio!!)

        infoEpisodeActivityViewModel.episodeInfoLiveData.observe(this,
            Observer<Episode> { episode->
                val binding: ActivityInfoEpisodeBinding =  DataBindingUtil.setContentView(this, R.layout.activity_info_episode)
                binding.episode=episode;
            }
        );

        infoEpisodeActivityViewModel.getEpisodeData(urlEpisodio);
    }
}