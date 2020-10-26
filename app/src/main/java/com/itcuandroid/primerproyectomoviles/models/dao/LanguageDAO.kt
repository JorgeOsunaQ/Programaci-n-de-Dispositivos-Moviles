package com.itcuandroid.primerproyectomoviles.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itcuandroid.primerproyectomoviles.models.entities.Language

@Dao
interface LanguageDAO {

    @Insert
    suspend fun insertLanguage(language: Language): Long

    @Query("SELECT * FROM Language")
    suspend fun getAllLanguages() : List<Language>
}