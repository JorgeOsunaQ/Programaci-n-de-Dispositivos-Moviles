package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Department (
    @PrimaryKey()
    val id: Long,
    val nameDepart: String
)

