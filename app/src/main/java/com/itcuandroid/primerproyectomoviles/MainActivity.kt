package com.itcuandroid.primerproyectomoviles

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_custom_dialog.*

class MainActivity : AppCompatActivity() {

    //Valores de Salida
    private lateinit var department:String
    private lateinit var selectedLanguages:MutableList<Language>

    //Edit Texts
    private lateinit var txtEditName: EditText
    private lateinit var  txtEditLastName: EditText
    private lateinit var  txtEditEmail: EditText

    //Radio Buttons
    private lateinit var  radioSistemas: RadioButton
    private lateinit var  radioAdmin: RadioButton
    private lateinit var  radioVentas: RadioButton

    //Check Box
    private lateinit var  checkEspanol: CheckBox
    private lateinit var  checkIngles: CheckBox
    private lateinit var  checkChino: CheckBox
    private lateinit var  checkFrances: CheckBox
    private lateinit var  checkAleman: CheckBox

    //RecyclerView
    private lateinit var recyclerLanguages: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        department="No seleccionado"
        selectedLanguages= mutableListOf<Language>()


        txtEditName=findViewById(R.id.userName)
        txtEditName.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)

        txtEditLastName=findViewById(R.id.userLastName)
        txtEditLastName.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)

        txtEditEmail=findViewById(R.id.userEmail)

        radioSistemas= findViewById(R.id.radioSistemas)
        radioAdmin= findViewById(R.id.radioAdmin)
        radioVentas= findViewById(R.id.radioVentas)

        radioSistemas.setOnCheckedChangeListener(radioChecked)
        radioAdmin.setOnCheckedChangeListener(radioChecked)
        radioVentas.setOnCheckedChangeListener(radioChecked)

        checkEspanol= findViewById(R.id.ckEspanol)
        checkIngles= findViewById(R.id.ckIngles)
        checkChino= findViewById(R.id.ckChino)
        checkFrances= findViewById(R.id.ckFrancés)
        checkAleman= findViewById(R.id.ckAleman)

        checkEspanol.setOnCheckedChangeListener(changeChecked)
        checkIngles.setOnCheckedChangeListener(changeChecked)
        checkChino.setOnCheckedChangeListener(changeChecked)
        checkAleman.setOnCheckedChangeListener(changeChecked)
        checkFrances.setOnCheckedChangeListener(changeChecked)

        recyclerLanguages=findViewById(R.id.recycler_languages)
        recyclerLanguages.layoutManager=LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        val floatingActionButton= findViewById<FloatingActionButton>(R.id.fltBtnSend)
        floatingActionButton.setOnClickListener(fabClick)
    }

    private val fabClick= View.OnClickListener { fab->
        val bundle = Bundle()

        var languages= ArrayList<String>()

        selectedLanguages.forEachIndexed { index,language ->
            languages.add(language.title)
        }

        bundle.putString("department",department?:"")
        bundle.putString("name",txtEditName.text.toString()?:"")
        bundle.putString("lastName",txtEditLastName.text.toString()?:"")
        bundle.putString("email",txtEditEmail.text.toString()?:"")
        bundle.putStringArrayList("languages",languages)

        Log.d("PRUEBA ARRAY",languages.toString())

        var customDialog= InfoDialog()
        customDialog.arguments=bundle

        customDialog.show(supportFragmentManager, "example")
    }

    val changeChecked= CompoundButton.OnCheckedChangeListener{ button, checked->
        val languages= mutableListOf<Language>()
        selectedLanguages.clear()

        if(checkEspanol.isChecked){
            languages.add(
                Language(
                    title = "Español",
                    image = R.drawable.ic_lengua_espanola
                )
            )
        }

        if(checkIngles.isChecked){
            languages.add(
                Language(
                    title = "Inglés",
                    image = R.drawable.ic_lengua_inglesa
                )
            )
        }

        if(checkChino.isChecked){
            languages.add(
                Language(
                    title = "Chino",
                    image = R.drawable.ic_lengua_china
                )
            )
        }

        if(checkFrances.isChecked){
            languages.add(
                Language(
                    title = "Francés",
                    image = R.drawable.ic_francia
                )
            )
        }

        if(checkAleman.isChecked){
            languages.add(
                Language(
                    title = "Aleman",
                    image = R.drawable.ic_alemania
                )
            )
        }

        selectedLanguages=languages
        var adapterLanguage=AdapterLanguages(selectedLanguages)
        recyclerLanguages.adapter=adapterLanguage
    }

    val radioChecked=CompoundButton.OnCheckedChangeListener { button, checked ->
        var departamento=""

        if(radioSistemas.isChecked){
            departamento="Sistemas\n"
        }else if(radioAdmin.isChecked){
            departamento="Administración\n"
        }else if(radioVentas.isChecked){
            departamento="Ventas\n"
        }

        department=departamento
    }

}