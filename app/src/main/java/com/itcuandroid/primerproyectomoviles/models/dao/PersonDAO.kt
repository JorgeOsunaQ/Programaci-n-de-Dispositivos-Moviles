package com.itcuandroid.primerproyectomoviles.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itcuandroid.primerproyectomoviles.models.entities.Person

@Dao
interface PersonDAO {

    @Insert
    suspend fun insertPerson(person: Person): Long

    @Query(
        "SELECT p.*, l.title as nbIdioma, l.image, p.nameDepart as nbDepa " +
                "FROM Person p" +
                "Inner Join Language l On l.id=p.idLanguage" +
                "Inner Join Department d On d.id=p.idDepartment"
    )
    fun getAllPersons() : LiveData<List<PersonData>>

    @Query("SELECT * FROM Person")
    suspend fun getAllPersonsSync(): List<Person>

    data class PersonData (
        val id : Long,
        val lastName: String,
        val email: String,
        val idDepartment: Long,
        val nbIdioma: String,
        val image: Int,
        val nbDepa: String
    )

}