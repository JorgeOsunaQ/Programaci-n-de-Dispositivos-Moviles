package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys =
    [
        ForeignKey(
            entity = Department::class,
            parentColumns = ["id"],
            childColumns = ["idDepartment"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]

)
data class Person (
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val name: String,
    val lastName: String,
    val email: String,
    val idLanguage: Long,
    val idDepartment: Long
){
    var fullName = "$name $lastName"

}

