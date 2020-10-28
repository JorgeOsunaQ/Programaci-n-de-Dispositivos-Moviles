package com.itcuandroid.primerproyectomoviles.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.itcuandroid.primerproyectomoviles.models.dao.PersonDAO
import com.itcuandroid.primerproyectomoviles.models.entities.*
import com.itcuandroid.primerproyectomoviles.respositories.PersonRepository
import com.itcuandroid.primerproyectomoviles.views.MainActivity
import kotlinx.coroutines.launch
import com.itcuandroid.primerproyectomoviles.respositories.DepartmentRepository
import com.itcuandroid.primerproyectomoviles.respositories.LanguageRepository
import com.itcuandroid.primerproyectomoviles.respositories.PersonWithLanguagesRepository
import java.lang.Exception

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val departmentRepository = DepartmentRepository(application)
    private val languageRepository = LanguageRepository(application)
    private val personLanguageRepository = PersonWithLanguagesRepository(application)

    private val TAG = MainActivity::class.java.name
    private val insertLiveData= MutableLiveData<Boolean>()

    fun insertPerson( person: Person, languages: List<Language> ) = viewModelScope.launch {
        try {
            personLanguageRepository.insertPersonLanguages(person,languages)
            insertLiveData.postValue(true)
        }catch (ex: Exception){
            Log.e(TAG, ex.message, ex)
            insertLiveData.postValue(false)
        }
    }

    fun notifyInsertContent() : LiveData<Boolean> = insertLiveData

    fun getDepartments() : LiveData<List<Department>> = liveData {
        emit(departmentRepository.getDepartments())
    }

    fun getDepartmentById(id:Long) : LiveData<Department> = liveData {
        emit(departmentRepository.getDepartmentById(id))
    }

    fun getLanguages() : LiveData<List<Language>> = liveData {
        emit(languageRepository.getLanguages())
    }
}