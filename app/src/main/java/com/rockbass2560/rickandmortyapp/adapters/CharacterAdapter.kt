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

    private val listCharacters = mutableListOf<CharacterView>()

    public fun addResults(data: List<CharacterView>) {
        listCharacters.addAll(data);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val cardDataBinding = CardCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(cardDataBinding);
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val result = listCharacters[position];
        holder.onBind(result);
    }

    override fun getItemCount(): Int {
        return listCharacters.size;
    }

    inner class CharacterViewHolder(private val cardCharacterBinding: CardCharacterBinding)
        : RecyclerView.ViewHolder(cardCharacterBinding.root) {

        fun onBind(characterView: CharacterView) {
            cardCharacterBinding.character = characterView
            cardCharacterBinding.executePendingBindings()
        }

    }
}