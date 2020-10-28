package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PersonWithLanguages(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "idPerson",
        entityColumn = "idLanguage",
        associateBy = Junction(PersonLanguage::class)
    )
    val languages: List<Language>
)
