package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys =
    [
        ForeignKey(
            entity = Department::class,
            parentColumns = ["id"],
            childColumns = ["idDepartment"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [Index(value = ["idDepartment"])]

)
data class Person (
    @PrimaryKey(autoGenerate = true)
    val idPerson: Long?=null,
    val name: String,
    val lastName: String,
    val email: String,
    val idDepartment: Long
){
    var fullName = "$name $lastName"

}

