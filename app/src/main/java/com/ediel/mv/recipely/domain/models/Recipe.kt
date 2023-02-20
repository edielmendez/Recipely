package com.ediel.mv.recipely.domain.models

data class Recipe(
    val name: String,
    val image: String,
    val description: String,
    var calories: Int,
    var carbs: Int,
    var proteins: Int,
    var fats: Int,
    val latitude: String,
    val longitude: String,
    var ingredients: List<Ingredient>,
    var instructions: List<Instruction>
)
