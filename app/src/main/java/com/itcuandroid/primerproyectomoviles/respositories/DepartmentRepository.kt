package com.itcuandroid.primerproyectomoviles.respositories

import android.content.Context
import com.itcuandroid.primerproyectomoviles.models.entities.Department
import com.itcuandroid.primerproyectomoviles.models.roomdb.PersonDB

class DepartmentRepository(context:Context) {
    private var personDB= PersonDB.getInstance(context)
    private var departmentDAO=personDB.departmentDao()

    suspend fun insertDepartment(department: Department) {
         departmentDAO.insertDepartment(department)
    }

    suspend fun getDepartments():  List<Department> {
        return departmentDAO.getAllDepartments()
    }

}