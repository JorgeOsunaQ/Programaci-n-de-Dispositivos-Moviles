package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithLanguagesAndDepartment (
    @Embedded  val persons: PersonWithLanguages,
    @Relation(
        entity = Department::class,
        parentColumn = "idDepartment",
        entityColumn = "id"
    )
    val department: Department
) {

}

