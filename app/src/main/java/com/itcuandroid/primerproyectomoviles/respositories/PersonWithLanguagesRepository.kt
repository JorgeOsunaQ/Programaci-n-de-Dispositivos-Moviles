package com.itcuandroid.primerproyectomoviles.respositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.itcuandroid.primerproyectomoviles.models.entities.*
import com.itcuandroid.primerproyectomoviles.models.roomdb.PersonDB

class PersonWithLanguagesRepository(context: Context) {

    private var personDB= PersonDB.getInstance(context)
    private var personDAO=personDB.personDAO()
    private var personLanguageDAO=personDB.personLanguageDAO()

    suspend fun insertPersonLanguages(person: Person, languages: List<Language>) {

        personDB.withTransaction {
            val idPerson = personDAO.insertPerson(person)
            val languagesList = languages.map {
                PersonLanguage(idPerson, it.idLanguage!!)
            }

            personLanguageDAO.insertPersonLanguages(languagesList)
        }
    }

    fun getPersonsLanguages() : LiveData<List<PersonWithLanguagesAndDepartment>> = personDAO.getPersonsWithLanguagesAndDepartments()

}