package com.itcuandroid.primerproyectomoviles.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Language (
    @PrimaryKey()
    val id: Long,
    val title:String,
    val image: Int,
)