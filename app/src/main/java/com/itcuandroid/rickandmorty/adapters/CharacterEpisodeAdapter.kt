package com.itcuandroid.rickandmorty.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itcuandroid.rickandmorty.databinding.CardCharacterBinding
import com.itcuandroid.rickandmorty.databinding.CardCharacterEpisodeBinding
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.models.Results
import com.itcuandroid.rickandmorty.views.InfoEpisodeActivity

class CharacterEpisodeAdapter: RecyclerView.Adapter<CharacterEpisodeAdapter.CharacterViewHolder>() {

    private val listCharacters = mutableListOf<Results>()

    fun addResults(listCharacters: List<Results>) {
        this.listCharacters.addAll(listCharacters);
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): CharacterEpisodeAdapter.CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);

        val cardBinding = CardCharacterEpisodeBinding.inflate(layoutInflater,parent, false);
        return CharacterViewHolder(cardBinding);
    }

    override fun onBindViewHolder(holder: CharacterEpisodeAdapter.CharacterViewHolder, position: Int) {
        val character= listCharacters[position]
        holder.onBind(character)
    }

    override fun getItemCount(): Int {
        return listCharacters.size;
    }

    inner class CharacterViewHolder(private val cardCharacterEpisodeBinding: CardCharacterEpisodeBinding) :
        RecyclerView.ViewHolder(cardCharacterEpisodeBinding.root) {
        fun onBind(character: Results) {

            cardCharacterEpisodeBinding.character=character;
            cardCharacterEpisodeBinding.executePendingBindings()

        }
    }
}