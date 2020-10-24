package com.itcuandroid.primerproyectomoviles

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class InfoDialog: DialogFragment() {

    //Text Views
    private lateinit var txtName: TextView
    private lateinit var  txtLastNameText: TextView
    private lateinit var  txtEmail: TextView
    private lateinit var  txtDepartment: TextView
    private lateinit var  txtLaguages: TextView

    //Recycler View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater= it.layoutInflater
            val viewCustomDialog=inflater.inflate(R.layout.layout_custom_dialog,null)

            builder.setView(viewCustomDialog)
                .setTitle("Información del usuario")

            txtDepartment=viewCustomDialog.findViewById(R.id.txtDepartamentoText)
            txtDepartment.text= arguments?.getString("department")

            txtName=viewCustomDialog.findViewById(R.id.txtNameText)
            txtName.text= arguments?.getString("name")

            txtLastNameText=viewCustomDialog.findViewById(R.id.txtLastNameText)
            txtLastNameText.text= arguments?.getString("lastName")

            txtEmail=viewCustomDialog.findViewById(R.id.txtEmailText)
            txtEmail.text= arguments?.getString("email")

            txtLaguages=viewCustomDialog.findViewById(R.id.txtLaguages)

            var languagesList=arguments?.getStringArrayList("languages")
            var languages=""

            languagesList?.forEachIndexed{index,language->
                languages+=language+"\n"
            }

            txtLaguages.text=if(languages=="") "Ningún idioma seleccionado" else languages

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}