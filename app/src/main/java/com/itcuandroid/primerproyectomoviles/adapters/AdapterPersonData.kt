package com.itcuandroid.primerproyectomoviles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itcuandroid.primerproyectomoviles.R
import com.itcuandroid.primerproyectomoviles.models.entities.PersonWithLanguagesAndDepartment

class AdapterPersonData(private val personData : List<PersonWithLanguagesAndDepartment>): RecyclerView.Adapter<AdapterPersonData.PersonDataHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonDataHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val view= layoutInflater.inflate(R.layout.card_person_data,parent,false)

        return AdapterPersonData.PersonDataHolder(view)
    }

    override fun onBindViewHolder(holder: PersonDataHolder, position: Int) {
        val personData= personData[position]
        holder.onBind(personData)
    }

    override fun getItemCount(): Int {
        return personData.size
    }

    class PersonDataHolder(val view : View):RecyclerView.ViewHolder(view){

        fun onBind(personData: PersonWithLanguagesAndDepartment){
            val name= view.findViewById<TextView>(R.id.textview_name)
            val email= view.findViewById<TextView>(R.id.textview_email)
            val department= view.findViewById<TextView>(R.id.textview_department)
            val languages= view.findViewById<TextView>(R.id.textview_languages)

            name.text= personData.persons.person.fullName
            email.text= personData.persons.person.email
            department.text= personData.department.nameDepart

            var textLanguages=""
            personData.persons.languages.forEachIndexed{index,lang->
                textLanguages+=lang.title+"\n"
            }
            languages.text=textLanguages
        }
    }
}