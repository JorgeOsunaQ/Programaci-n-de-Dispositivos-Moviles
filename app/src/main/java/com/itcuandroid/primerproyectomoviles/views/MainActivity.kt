package com.itcuandroid.primerproyectomoviles.views

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.itcuandroid.primerproyectomoviles.models.entities.Language
import com.itcuandroid.primerproyectomoviles.R
import com.itcuandroid.primerproyectomoviles.adapters.AdapterLanguages
import com.itcuandroid.primerproyectomoviles.models.entities.Person
import com.itcuandroid.primerproyectomoviles.viewModels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    //ViewModels
    val viewModel : MainActivityViewModel by viewModels()

    //Valores de Salida
    private var idDepartment:Long=0
    private lateinit var selectedLanguages:MutableList<Language>

    //Edit Texts
    private lateinit var txtEditName: EditText
    private lateinit var  txtEditLastName: EditText
    private lateinit var  txtEditEmail: EditText

    //Radio Buttons
    private lateinit var  radioSistemas: RadioButton
    private lateinit var  radioAdmin: RadioButton
    private lateinit var  radioVentas: RadioButton
    private lateinit var  radioGroup: RadioGroup

    //Check Box
    private lateinit var  checkEspanol: CheckBox
    private lateinit var  checkIngles: CheckBox
    private lateinit var  checkChino: CheckBox
    private lateinit var  checkFrances: CheckBox
    private lateinit var  checkAleman: CheckBox

    //RecyclerView
    private lateinit var recyclerLanguages: RecyclerView

    private lateinit var languages: List<Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idDepartment=0
        selectedLanguages= mutableListOf<Language>()


        txtEditName=findViewById(R.id.userName)
        txtEditName.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)

        txtEditLastName=findViewById(R.id.userLastName)
        txtEditLastName.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)

        txtEditEmail=findViewById(R.id.userEmail)

        radioSistemas= findViewById(R.id.radioSistemas)
        radioAdmin= findViewById(R.id.radioAdmin)
        radioVentas= findViewById(R.id.radioVentas)
        radioGroup= findViewById(R.id.radioGroupDepa)

        radioSistemas.setOnCheckedChangeListener(radioChecked)
        radioAdmin.setOnCheckedChangeListener(radioChecked)
        radioVentas.setOnCheckedChangeListener(radioChecked)

        checkEspanol= findViewById(R.id.ckEspanol)
        checkIngles= findViewById(R.id.ckIngles)
        checkChino= findViewById(R.id.ckChino)
        checkFrances=findViewById(R.id.ckFrances)
        checkAleman=findViewById(R.id.ckAleman)

        checkEspanol.setOnCheckedChangeListener(changeChecked)
        checkIngles.setOnCheckedChangeListener(changeChecked)
        checkChino.setOnCheckedChangeListener(changeChecked)
        checkAleman.setOnCheckedChangeListener(changeChecked)
        checkFrances.setOnCheckedChangeListener(changeChecked)

        recyclerLanguages=findViewById(R.id.recycler_languages)
        recyclerLanguages.layoutManager=LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val floatingActionButton= findViewById<FloatingActionButton>(R.id.fltBtnSend)
        floatingActionButton.setOnClickListener(fabClick)

        val floatingActionSearch= findViewById<FloatingActionButton>(R.id.fltBtnSearch)
        floatingActionSearch.setOnClickListener(fabSearchClick)

        viewModel.getLanguages().observe(this,{lang->
            languages=lang
        })

        viewModel.notifyInsertContent().observe(this,{succesful->
            if (succesful) {
                Toast.makeText(this, "Guardado exitoso", Toast.LENGTH_LONG).show()
                clearControls()
            } else {
                Toast.makeText(this, "No se pudo completar el registro", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun clearControls(){
        txtEditName.setText("")
        txtEditLastName.setText("")
        txtEditEmail.setText("")
        radioGroup.clearCheck()

        checkEspanol.isChecked=false
        checkIngles.isChecked=false
        checkChino.isChecked=false
        checkAleman.isChecked=false
        checkFrances.isChecked=false

    }

    private val fabClick= View.OnClickListener { fab->
        val bundle = Bundle()

        var languages= ArrayList<String>()

        selectedLanguages.forEachIndexed { index, language ->
            languages.add(language.title)
        }

        viewModel.getDepartmentById(idDepartment).observe(this,{depa->
            var department=depa

            bundle.putString("department", department.nameDepart ?: "")
            bundle.putString("name", txtEditName.text.toString() ?: "")
            bundle.putString("lastName", txtEditLastName.text.toString() ?: "")
            bundle.putString("email", txtEditEmail.text.toString() ?: "")
            bundle.putStringArrayList("languages", languages)

            Log.d("PRUEBA ARRAY", languages.toString())

            var person= Person(
                name = txtEditName.text.toString(),
                lastName = txtEditLastName.text.toString(),
                email = txtEditEmail.text.toString(),
                idDepartment = department.id ?: 0
            )

            viewModel.insertPerson(person,selectedLanguages)

            var customDialog= InfoDialog()
            customDialog.arguments=bundle

            customDialog.show(supportFragmentManager, "example")
        })


    }

    private val fabSearchClick= View.OnClickListener { fab->
        val intent = Intent(this, ShowDataActivity::class.java)
        startActivity(intent)
    }

    val changeChecked= CompoundButton.OnCheckedChangeListener{ button, checked->
        selectedLanguages.clear()

        if(checkEspanol.isChecked){
            selectedLanguages.add(
                languages.filter { lang-> lang.idLanguage == 1L }[0]
            )
        }

        if(checkIngles.isChecked){
            selectedLanguages.add(
               languages.filter { lang-> lang.idLanguage == 2L }[0]
            )
        }

        if(checkChino.isChecked){
            selectedLanguages.add(
                languages.filter { lang-> lang.idLanguage == 3L }[0]
            )
        }

        if(checkFrances.isChecked){
            selectedLanguages.add(
                languages.filter { lang-> lang.idLanguage == 4L }[0]
            )
        }

        if(checkAleman.isChecked){
            selectedLanguages.add(
                languages.filter { lang-> lang.idLanguage == 5L }[0]
            )
        }

        Log.d("Lenguages",selectedLanguages.toString())

        var adapterLanguage= AdapterLanguages(selectedLanguages)
        recyclerLanguages.adapter=adapterLanguage
    }

    val radioChecked=CompoundButton.OnCheckedChangeListener { button, checked ->
        var departamento:Long=0

        if(radioSistemas.isChecked){
            departamento=1
        }else if(radioAdmin.isChecked){
            departamento=2
        }else if(radioVentas.isChecked){
            departamento=3
        }

        idDepartment=departamento
    }

}