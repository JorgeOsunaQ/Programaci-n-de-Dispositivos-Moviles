package com.itcuandroid.rickandmorty.models

data class CharacterView (
    var image: String,
    var name: String,
    var status: String,
    var specie: String,
    var location: String,
    var firstSeen: String,
    var episodeUrl: String? = null
)