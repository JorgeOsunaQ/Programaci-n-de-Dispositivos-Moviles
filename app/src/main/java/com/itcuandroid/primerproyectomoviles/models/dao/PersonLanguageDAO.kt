package com.itcuandroid.primerproyectomoviles.models.dao

import androidx.room.Dao
import androidx.room.Insert
import com.itcuandroid.primerproyectomoviles.models.entities.Language
import com.itcuandroid.primerproyectomoviles.models.entities.Person
import com.itcuandroid.primerproyectomoviles.models.entities.PersonLanguage

@Dao
interface PersonLanguageDAO {

    @Insert
    suspend fun insertPersonLanguage(personLang: PersonLanguage): Long

    @Insert
    suspend fun insertPersonLanguages(personLangs: List<PersonLanguage>)

}