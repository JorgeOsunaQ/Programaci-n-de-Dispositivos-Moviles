package com.itcuandroid.primerproyectomoviles.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.itcuandroid.primerproyectomoviles.models.dao.PersonDAO
import com.itcuandroid.primerproyectomoviles.models.entities.Department
import com.itcuandroid.primerproyectomoviles.models.entities.Language
import com.itcuandroid.primerproyectomoviles.respositories.PersonRepository
import com.itcuandroid.primerproyectomoviles.views.MainActivity
import kotlinx.coroutines.launch
import com.itcuandroid.primerproyectomoviles.models.entities.Person
import com.itcuandroid.primerproyectomoviles.respositories.DepartmentRepository
import com.itcuandroid.primerproyectomoviles.respositories.LanguageRepository
import java.lang.Exception

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application)
    private val departmentRepository = DepartmentRepository(application)
    private val languageRepository = LanguageRepository(application)

    private val TAG = MainActivity::class.java.name
    private val insertLiveData= MutableLiveData<Boolean>()

    fun insertPerson( person: Person, idDepartment: Int, idLanguage: Int ) = viewModelScope.launch {
        try {
            personRepository.insertPerson(person)
            insertLiveData.postValue(true)
        }catch (ex: Exception){
            Log.e(TAG, ex.message, ex)
            insertLiveData.postValue(false)
        }
    }

    fun notifyInsertContent() : LiveData<Boolean> = insertLiveData

    fun getPersons() : LiveData<List<PersonDAO.PersonData>> = personRepository.getAllPersonLiveData()

    fun getDepartments() : LiveData<List<Department>> = liveData {
        emit(departmentRepository.getDepartments())
    }

    fun getLanguages() : LiveData<List<Language>> = liveData {
        emit(languageRepository.getLanguages())
    }
}