package com.itcuandroid.primerproyectomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterLanguages(private val languajes: List<Language>): RecyclerView.Adapter<AdapterLanguages.LanguajesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLanguages.LanguajesHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val view= layoutInflater.inflate(R.layout.card_languages,parent,false)

        return LanguajesHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterLanguages.LanguajesHolder, position: Int) {
        val language= languajes[position]
        holder.onBind(language)
    }

    override fun getItemCount(): Int {
        return languajes.size
    }

    class LanguajesHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun onBind(language: Language){
            val image= view.findViewById<ImageView>(R.id.image)
            val title= view.findViewById<TextView>(R.id.title)

            image.setImageResource(language.image)
            title.text= language.title
        }
    }

}
