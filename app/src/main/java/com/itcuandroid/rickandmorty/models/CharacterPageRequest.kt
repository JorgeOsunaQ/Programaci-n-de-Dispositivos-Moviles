package com.itcuandroid.rickandmorty.models

import com.google.gson.annotations.SerializedName


data class CharacterPageRequest (

	@SerializedName("info") val info : Info,
	@SerializedName("results") val results : List<Results>
)