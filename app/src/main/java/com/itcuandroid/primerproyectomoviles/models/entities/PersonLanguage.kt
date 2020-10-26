package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    primaryKeys = [
        "idPerson", "idDepartment"
    ],
    foreignKeys =
    [
        ForeignKey(
            entity = Department::class,
            parentColumns = ["id"],
            childColumns = ["idDepartment"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["idPerson"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]

)
data class PersonLanguage (
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val idPerson: Long,
    val idDepartment: Long
){

}


