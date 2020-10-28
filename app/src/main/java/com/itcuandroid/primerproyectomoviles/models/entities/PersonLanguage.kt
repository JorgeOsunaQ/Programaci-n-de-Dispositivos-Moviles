package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = [
        "idPerson", "idLanguage"
    ],
    indices = [Index(value = ["idLanguage","idPerson"])],
    foreignKeys =
    [
        ForeignKey(
            entity = Language::class,
            parentColumns = ["idLanguage"],
            childColumns = ["idLanguage"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Person::class,
            parentColumns = ["idPerson"],
            childColumns = ["idPerson"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]

)
data class PersonLanguage (
    val idPerson: Long,
    val idLanguage: Long
){



}


