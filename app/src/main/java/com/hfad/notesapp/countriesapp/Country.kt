package com.hfad.notesapp.countriesapp

data class Country(     // create data classes which connect with json API server
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flag: String
)

data class Language(
    val name: String
)