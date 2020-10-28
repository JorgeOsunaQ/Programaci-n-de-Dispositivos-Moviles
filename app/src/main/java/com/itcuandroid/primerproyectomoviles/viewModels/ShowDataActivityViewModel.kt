package com.itcuandroid.primerproyectomoviles.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.itcuandroid.primerproyectomoviles.models.entities.PersonWithLanguagesAndDepartment
import com.itcuandroid.primerproyectomoviles.respositories.PersonWithLanguagesRepository

class ShowDataActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val personLanguageRepository = PersonWithLanguagesRepository(application)

    fun getPersonsWithLangAndDepa() : LiveData<List<PersonWithLanguagesAndDepartment>> = personLanguageRepository.getPersonsLanguages()
}
