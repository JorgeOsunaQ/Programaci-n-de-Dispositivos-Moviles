package com.itcuandroid.mascotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_animals.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView= findViewById<RecyclerView>(R.id.recycler_view)
        val animalAdapter= AnimalAdapter(createAnimals())

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= animalAdapter

        animalAdapter.notifyDataSetChanged()
    }

    fun createAnimals(): List<Animal>{
        val animals= mutableListOf<Animal>()

        animals.add(
            Animal(
                image = R.drawable.ic_gallo,
                title = "Gallo",
                description = "Animales de granja comunes en comunidades rurales."
            )
        )

        animals.add(
            Animal(
                image = R.drawable.ic_dog,
                title = "Perro",
                description = "Animales domesticos muy apegados a la vida diaria del ser humano en casi toda sus especies."
            )
        )

        animals.add(
            Animal(
                image = R.drawable.ic_vaca,
                title = "Vaca",
                description = "Animales de granja que casi siempre se tienen con finalidad de consumo. Son animales grandes y fuertes."
            )
        )

        return animals;
    }
}