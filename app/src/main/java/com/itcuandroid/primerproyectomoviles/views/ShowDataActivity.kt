package com.itcuandroid.primerproyectomoviles.views

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itcuandroid.primerproyectomoviles.R
import com.itcuandroid.primerproyectomoviles.adapters.AdapterPersonData
import com.itcuandroid.primerproyectomoviles.viewModels.MainActivityViewModel
import com.itcuandroid.primerproyectomoviles.viewModels.ShowDataActivityViewModel
import kotlinx.android.synthetic.main.activity_show_data.view.*

class ShowDataActivity: AppCompatActivity() {

    //ViewModels
    val viewModel : ShowDataActivityViewModel by viewModels()

    //RecyclerView
    private lateinit var recyclerPerson: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        recyclerPerson = findViewById(R.id.recyclerPersonData)
        recyclerPerson.layoutManager = LinearLayoutManager(this)

        viewModel.getPersonsWithLangAndDepa().observe(this,{data->
            val personDataAdapter = AdapterPersonData(data)

            recyclerPerson.adapter = personDataAdapter

            personDataAdapter.notifyDataSetChanged()
        })
    }
}
