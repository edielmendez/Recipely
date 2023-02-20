package com.ediel.mv.recipely.data.models

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    @SerializedName("name"         ) var name         : String?           = null,
    @SerializedName("image"        ) var image        : String?           = null,
    @SerializedName("description"  ) var description  : String?           = null,
    @SerializedName("calories"     ) var calories     : Int?              = null,
    @SerializedName("carbs"        ) var carbs        : Int?              = null,
    @SerializedName("proteins"     ) var proteins     : Int?              = null,
    @SerializedName("fats"         ) var fats         : Int?              = null,
    @SerializedName("ingredients"  ) var ingredients  : List<String> = listOf(),
    @SerializedName("instructions" ) var instructions : List<String> = listOf()
)
