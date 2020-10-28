package com.itcuandroid.primerproyectomoviles.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.itcuandroid.primerproyectomoviles.models.entities.Person
import com.itcuandroid.primerproyectomoviles.models.entities.PersonWithLanguages
import com.itcuandroid.primerproyectomoviles.models.entities.PersonWithLanguagesAndDepartment

@Dao
interface PersonDAO {

    @Insert
    suspend fun insertPerson(person: Person): Long

    @Query("SELECT * FROM Person")
    fun getAllPersons() : LiveData<List<Person>>

    @Transaction
    @Query("SELECT * FROM Person")
    fun getPersonsWithLanguagesAndDepartments(): LiveData<List<PersonWithLanguagesAndDepartment>>

    @Query("SELECT * FROM Person")
    suspend fun getAllPersonsSync(): List<Person>
}