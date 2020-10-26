package com.itcuandroid.primerproyectomoviles.respositories

import android.content.Context
import com.itcuandroid.primerproyectomoviles.models.entities.Department
import com.itcuandroid.primerproyectomoviles.models.entities.Language
import com.itcuandroid.primerproyectomoviles.models.roomdb.PersonDB

class LanguageRepository(context: Context) {
    private var personDB= PersonDB.getInstance(context)
    private var languageDAO=personDB.languageDAO()

    suspend fun insertLanguage(language: Language) {
        languageDAO.insertLanguage(language)
    }

    suspend fun getLanguages(): List<Language> {
        return languageDAO.getAllLanguages()
    }
}