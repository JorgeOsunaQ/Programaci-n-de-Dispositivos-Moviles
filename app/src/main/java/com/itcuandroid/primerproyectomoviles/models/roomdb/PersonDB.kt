package com.itcuandroid.primerproyectomoviles.models.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itcuandroid.primerproyectomoviles.R
import com.itcuandroid.primerproyectomoviles.models.dao.DepartmentDAO
import com.itcuandroid.primerproyectomoviles.models.dao.LanguageDAO
import com.itcuandroid.primerproyectomoviles.models.dao.PersonDAO
import com.itcuandroid.primerproyectomoviles.models.entities.Department
import com.itcuandroid.primerproyectomoviles.models.entities.Language
import com.itcuandroid.primerproyectomoviles.models.entities.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Department::class, Language::class, Person::class],
    version = 1,
    exportSchema = true)
abstract class PersonDB : RoomDatabase() {
    abstract  fun departmentDao(): DepartmentDAO
    abstract  fun languageDAO(): LanguageDAO
    abstract  fun personDAO(): PersonDAO


    companion object {
        @JvmStatic
        @Volatile
        private var instance: PersonDB? = null

        @Synchronized
        fun getInstance(context: Context): PersonDB {
            if(instance == null){
                instance= Room.databaseBuilder(
                    context,
                    PersonDB::class.java,
                    "person.db")
                .fallbackToDestructiveMigration()
                .build()

                CoroutineScope(Dispatchers.IO).launch {
                    instance?.initDB();
                }
            }
            return instance as PersonDB;
        }
    }

    suspend fun initDB(){
        val languageDAO = languageDAO()
        val depaDAO = departmentDao()
        val personDAO= personDAO()

        if(languageDAO.getAllLanguages().isEmpty()){
            languageDAO.insertLanguage(
                Language(
                    1,
                    title = "Español",
                    image = R.drawable.ic_lengua_espanola
                )
            )

            languageDAO.insertLanguage(
                Language(
                    2,
                    title = "Inglés",
                    image = R.drawable.ic_lengua_inglesa
                )
            )

            languageDAO.insertLanguage(
                Language(
                    3,
                    title = "Chino",
                    image = R.drawable.ic_lengua_china
                )
            )
        }

        if(depaDAO.getAllDepartments().isEmpty()){

        }

        if(personDAO.getAllPersonsSync().isEmpty()){

        }


    }
}