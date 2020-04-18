package com.example.eatanddrink.model.drink
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.squareup.moshi.Json


@Keep
data class DrinkData(
    val drinks: List<DrinkDataX>
)

@Keep
@Entity
data class DrinkDataX(
    val dateModified: String?=null,
    @PrimaryKey
    val idDrink: String,
    val strAlcoholic: String?=null,
    val strCategory: String?=null,
    val strCreativeCommonsConfirmed: String?=null,
    val strDrink: String?=null,
    val strDrinkAlternate: String?=null,
    val strDrinkDE:String?=null,
    val strDrinkES: String?=null,
    val strDrinkFR: String?=null,
    val strDrinkThumb: String?=null,
    @Json(name = "strDrinkZH-HANS")
    val strDrinkZHHANS: String?=null,
    @Json(name = "strDrinkZH-HANT")
    val strDrinkZHHANT: String?=null,
    val strGlass: String?=null,
    val strIBA: String?=null,
    val strIngredient1: String?=null,
    val strIngredient10: String?=null,
    val strIngredient11: String?=null,
    val strIngredient12: String?=null,
    val strIngredient13: String?=null,
    val strIngredient14: String?=null,
    val strIngredient15: String?=null,
    val strIngredient2:String?=null,
    val strIngredient3: String?=null,
    val strIngredient4: String?=null,
    val strIngredient5: String?=null,
    val strIngredient6: String?=null,
    val strIngredient7: String?=null,
    val strIngredient8: String?=null,
    val strIngredient9: String?=null,
    val strInstructions: String?=null,
    val strInstructionsDE: String?=null,
    val strInstructionsES: String?=null,
    val strInstructionsFR: String?=null,
    @Json(name = "strInstructionsZH-HANS")
    val strInstructionsZHHANS: String?=null,
    @Json(name = "strInstructionsZH-HANT")
    val strInstructionsZHHANT: String?=null,
    val strMeasure1: String?=null,
    val strMeasure10: String?=null,
    val strMeasure11: String?=null,
    val strMeasure12: String?=null,
    val strMeasure13: String?=null,
    val strMeasure14: String?=null,
    val strMeasure15: String?=null,
    val strMeasure2: String?=null,
    val strMeasure3: String?=null,
    val strMeasure4: String?=null,
    val strMeasure5: String?=null,
    val strMeasure6: String?=null,
    val strMeasure7: String?=null,
    val strMeasure8: String?=null,
    val strMeasure9: String?=null,
    val strTags: String?=null,
    val strVideo:String?=null
)