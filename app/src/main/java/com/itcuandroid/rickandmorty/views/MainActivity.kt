package com.itcuandroid.rickandmorty.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itcuandroid.rickandmorty.R
import com.itcuandroid.rickandmorty.adapters.CharacterAdapter
import com.itcuandroid.rickandmorty.listeners.OnBottomReachedListener
import com.itcuandroid.rickandmorty.models.CharacterView
import com.itcuandroid.rickandmorty.viewmodels.MainActivityViewModel

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("statusColor")
fun changeColorFromStatus(textView: TextView, status: String) {
    when (status) {
        "Alive" -> {
            textView.setTextColor(Color.GREEN)
        }
        "Dead" -> {
            textView.setTextColor(Color.RED)
        }
        else -> {
            textView.setTextColor(Color.GRAY)
        }
    }
}

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel :  MainActivityViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewData = findViewById<RecyclerView>(R.id.recyclerViewData);
        val characterAdapter = CharacterAdapter()

        recyclerViewData.layoutManager = LinearLayoutManager(this)
        recyclerViewData.adapter = characterAdapter

        mainActivityViewModel.rickAndMortyLiveData.observe(this,
            Observer<List<CharacterView>> { it->
                characterAdapter.addResults(it, mainActivityViewModel.hasNextCharacters)
                characterAdapter.notifyDataSetChanged()
            })

        characterAdapter.setOnBottomReachedListener(object: OnBottomReachedListener {
            override fun onBottomReached() {
                mainActivityViewModel.getCharacters()
            }
        })

        mainActivityViewModel.getCharacters()
    }
}

