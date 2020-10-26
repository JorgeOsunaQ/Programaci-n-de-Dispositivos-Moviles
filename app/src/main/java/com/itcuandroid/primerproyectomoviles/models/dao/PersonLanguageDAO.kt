package com.itcuandroid.primerproyectomoviles.models.dao

import androidx.room.Dao
import androidx.room.Insert
import com.itcuandroid.primerproyectomoviles.models.entities.Person

@Dao
interface PersonLanguageDAO {

    @Insert
    suspend fun insertPersonLanguage(personLang: PersonLanguageDAO): Long
}