package com.hfad.notesapp.countriesapp

data class Country(
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>
)

data class Language (
    val name: String
    )