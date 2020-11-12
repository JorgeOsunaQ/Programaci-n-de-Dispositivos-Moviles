package com.itcuandroid.rickandmorty.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.itcuandroid.rickandmorty.R
import com.itcuandroid.rickandmorty.databinding.CardCharacterBinding
import com.itcuandroid.rickandmorty.listeners.OnBottomReachedListener
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.views.InfoEpisodeActivity


const val CIRCULAR_LOADING = 1
const val CARD_CHARACTER = 0

class CharacterAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listCharacters = mutableListOf<CharacterView>()
    private var hasNextCharacters = false
    private var onBottomReachedListener: OnBottomReachedListener? = null


    fun addResults(listCharacters: List<CharacterView>, hasNextCharacters: Boolean){
        this.listCharacters.addAll(listCharacters);
        this.hasNextCharacters = hasNextCharacters
    }

    override fun getItemViewType(position: Int): Int =
        if (position == listCharacters.size) {
            CIRCULAR_LOADING
        } else {
            CARD_CHARACTER
        }


    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        if (viewType == CARD_CHARACTER) {
            val cardDataBinding = CardCharacterBinding.inflate(layoutInflater, parent, false)
            return CharacterViewHolder(cardDataBinding)
        } else {
            val circularLoading = layoutInflater.inflate(
                R.layout.circular_loading_characters,
                parent,
                false
            )
            return CircularLoadingViewHolder(circularLoading)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            val result = listCharacters[position];
            holder.onBind(result);
        } else {
            onBottomReachedListener?.onBottomReached()
        }
    }

    override fun getItemCount(): Int {
        return listCharacters.size;
    }

    inner class CircularLoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) { }

    inner class CharacterViewHolder(private val cardCharacterBinding: CardCharacterBinding)
        : RecyclerView.ViewHolder(cardCharacterBinding.root){

        fun onBind(characterView: CharacterView) {
            cardCharacterBinding.character = characterView
            cardCharacterBinding.executePendingBindings()

            cardCharacterBinding.firsEpisodeSeen.setOnClickListener { click->

                val intent = Intent(this.itemView.context,InfoEpisodeActivity::class.java)
                intent.putExtra("Episode", characterView.episodeUrl);

                this.itemView.context.startActivity(intent)
            }
        }
    }

}