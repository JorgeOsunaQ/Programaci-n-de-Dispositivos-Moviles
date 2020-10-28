package com.itcuandroid.primerproyectomoviles.models.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itcuandroid.primerproyectomoviles.R
import com.itcuandroid.primerproyectomoviles.models.dao.DepartmentDAO
import com.itcuandroid.primerproyectomoviles.models.dao.LanguageDAO
import com.itcuandroid.primerproyectomoviles.models.dao.PersonDAO
import com.itcuandroid.primerproyectomoviles.models.dao.PersonLanguageDAO
import com.itcuandroid.primerproyectomoviles.models.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Department::class, Language::class, Person::class, PersonLanguage::class],
    version = 1,
    exportSchema = true)
abstract class PersonDB : RoomDatabase() {
    abstract  fun departmentDao(): DepartmentDAO
    abstract  fun languageDAO(): LanguageDAO
    abstract  fun personDAO(): PersonDAO
    abstract  fun personLanguageDAO(): PersonLanguageDAO


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


            languageDAO.insertLanguage(
                Language(
                    4,
                    title = "Fránces",
                    image = R.drawable.ic_francia
                )
            )


            languageDAO.insertLanguage(
                Language(
                    5,
                    title = "Aleman",
                    image = R.drawable.ic_alemania
                )
            )
        }

        if(depaDAO.getAllDepartments().isEmpty()){

            depaDAO.insertDepartment(
                Department(
                    1,
                    nameDepart = "Sistemas"
                )
            )

            depaDAO.insertDepartment(
                Department(
                    2,
                    nameDepart = "Administrativo"
                )
            )

            depaDAO.insertDepartment(
                Department(
                    3,
                    nameDepart = "Ventas"
                )
            )

        }

        if(personDAO.getAllPersonsSync().isEmpty()){
            val idP1=personDAO.insertPerson(
                Person(
                    name="Jorge",
                    lastName = "Osuna Quintana",
                    email = "jorgeosunaqui@gmail.com",
                    idDepartment = 1
                )
            )

            val idP2=personDAO.insertPerson(
                Person(
                    name="Carlos Iván",
                    lastName = "Osuna Quintana",
                    email = "correoTest@gmail.com",
                    idDepartment = 1
                )
            )

            val languagesPerson= listOf(PersonLanguage(idP1,1), PersonLanguage(idP1,2),PersonLanguage(idP2,1))
            personLanguageDAO().insertPersonLanguages(languagesPerson)
        }


    }
}