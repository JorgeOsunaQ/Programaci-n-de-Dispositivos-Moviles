package com.itcuandroid.primerproyectomoviles.respositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.itcuandroid.primerproyectomoviles.models.dao.PersonDAO
import com.itcuandroid.primerproyectomoviles.models.entities.Language
import com.itcuandroid.primerproyectomoviles.models.entities.Person
import com.itcuandroid.primerproyectomoviles.models.roomdb.PersonDB

class PersonRepository(context: Context) {
    private var personDB= PersonDB.getInstance(context)
    private var personDAO=personDB.personDAO()

    suspend fun insertPerson(person: Person, languages: List<Language>) {
        personDAO.insertPerson(person)
    }

    suspend fun getAllPersonsData() {
        personDAO.getAllPersonsSync()
    }

    fun getAllPersonLiveData(): LiveData<List<PersonDAO.PersonData>> {
        return personDAO.getAllPersons()
    }
}