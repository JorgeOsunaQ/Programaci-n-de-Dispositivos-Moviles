package com.itcuandroid.rickandmorty.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itcuandroid.rickandmorty.R
import com.itcuandroid.rickandmorty.adapters.CharacterAdapter
import com.itcuandroid.rickandmorty.adapters.CharacterEpisodeAdapter
import com.itcuandroid.rickandmorty.databinding.ActivityInfoEpisodeBinding
import com.itcuandroid.rickandmorty.models.Episode
import com.itcuandroid.rickandmorty.models.Results
import com.itcuandroid.rickandmorty.viewmodels.InfoEpisodeActivityViewModel
import kotlinx.android.synthetic.main.activity_info_episode.view.*
import java.net.URI

@BindingAdapter("android:tempEpisode")
fun setTempText(view: TextView, text: String?) {
    Log.d("TextDATA",text.toString())

    var labelTemp= getTempText(text);

    view.text=labelTemp;
}

class InfoEpisodeActivity : AppCompatActivity() {

    private val infoEpisodeActivityViewModel : InfoEpisodeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityInfoEpisodeBinding =  DataBindingUtil.setContentView(this, R.layout.activity_info_episode)

        val urlEpisodio = intent.getStringExtra("Episode");
        Log.d("UrlEpisodio", urlEpisodio!!)

        val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerViewDataEpisode);
        val characterEpisodeAdapter = CharacterEpisodeAdapter()

        recyclerViewData.layoutManager = LinearLayoutManager(this)
        recyclerViewData.adapter = characterEpisodeAdapter

        infoEpisodeActivityViewModel.episodeInfoLiveData.observe(this,
            Observer<Episode> { episode ->
                binding.episode = episode;
                binding.executePendingBindings()
                getCharactersFromEpisode(episode);
            }
        );

        infoEpisodeActivityViewModel.characterLiveData.observe(this,
            Observer<List<Results>> { characters ->
                characterEpisodeAdapter.addResults(characters)
                characterEpisodeAdapter.notifyDataSetChanged()
            }
        );

        infoEpisodeActivityViewModel.getEpisodeData(urlEpisodio);
    }

    fun getCharactersFromEpisode(episode: Episode){
        val characters= mutableListOf<Int>();

        episode.characters.map { characterUrl->
            val uri = URI(characterUrl)
            val path: String = uri.getPath()
            val idStr = path.substring(path.lastIndexOf('/') + 1)
            val idCharacter = idStr.toInt()

            characters.add(idCharacter);
        }
        infoEpisodeActivityViewModel.getCharactersEpisode(characters);
    }

}

fun getTempText(tempTex: String?): String {

    if(tempTex==null)
        return "";

    val pattern = Regex(pattern = "\\d+");
    val matches = pattern.findAll(tempTex);

    var temp="";
    var episode="";

    Log.d("REGEX","Inicia Regex")
    Log.d("REGEX",matches.toString())
    matches.forEachIndexed { index, f ->
        val m = f.value
        val idx = f.range
        Log.d("REGEX","$m found at indexes: $idx")

        if(index==0)
            temp=f.value;
        else
            episode=f.value
    }

    return "Temporada ${temp}, Episodio ${episode}";
}