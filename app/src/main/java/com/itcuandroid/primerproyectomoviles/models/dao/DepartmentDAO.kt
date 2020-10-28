package com.itcuandroid.primerproyectomoviles.models.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.itcuandroid.primerproyectomoviles.models.entities.Department
import com.itcuandroid.primerproyectomoviles.models.entities.Language

@Dao
interface DepartmentDAO {

    @Insert
    suspend fun insertDepartment(department: Department): Long

    @Query("SELECT * FROM Department")
    suspend fun getAllDepartments() : List<Department>

    @Query("SELECT * FROM Department where id=:id")
    suspend fun getDepartmentById(id:Long) : Department
}