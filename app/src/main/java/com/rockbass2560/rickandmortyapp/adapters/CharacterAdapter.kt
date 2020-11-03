package com.rockbass2560.rickandmortyapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rockbass2560.rickandmortyapp.R
import com.rockbass2560.rickandmortyapp.databinding.CardCharacterBinding
import com.rockbass2560.rickandmortyapp.models.CharacterPageRequest
import com.rockbass2560.rickandmortyapp.models.CharacterView
import com.rockbass2560.rickandmortyapp.models.Results

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val listCharacters = mutableListOf<Results>()

    public fun addResults(characterPageRequest: CharacterPageRequest) {
        val results = characterPageRequest.results;

        listCharacters.addAll(results);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val view = layoutInflater.inflate(R.layout.card_character, parent, false);
        return CharacterViewHolder(view);
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val result = listCharacters[position];
        holder.onBind(result);
    }

    override fun getItemCount(): Int {
        return listCharacters.size;
    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(result: Results) {
            val cardCharacterBinding = CardCharacterBinding.bind(itemView)

            cardCharacterBinding.character = CharacterView(
                result.image,
                result.name,
                result.status,
                result.species,
                result.location.name,
                result.episode.first()
            );
        }

    }
}