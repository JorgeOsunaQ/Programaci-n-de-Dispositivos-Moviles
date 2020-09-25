package com.itcuandroid.mascotas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(private val animals: List<Animal>) : RecyclerView.Adapter<AnimalAdapter.AnimalHolder>() {

    class AnimalHolder(val view: View): RecyclerView.ViewHolder(view){

        fun onBind(animal: Animal){
            val image= view.findViewById<ImageView>(R.id.image)
            val title= view.findViewById<TextView>(R.id.title)
            val description= view.findViewById<TextView>(R.id.description)

            image.setImageResource(animal.image)
            title.text= animal.title
            description.text= animal.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val view= layoutInflater.inflate(R.layout.card_animals, parent, false)

        return AnimalHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(animalHolder: AnimalHolder, position: Int) {
        val animal= animals[position]
        animalHolder.onBind(animal)
    }

}