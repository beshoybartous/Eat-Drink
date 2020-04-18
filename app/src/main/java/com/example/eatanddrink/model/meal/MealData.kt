package com.example.eatanddrink.model.meal
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey


//https://www.themealdb.com/api/json/v1/1/lookup.php?i=52959
@Keep
data class MealData(
    var meals: List<Meal>?=null
)

@Keep
@Entity
data class Meal(
    @PrimaryKey
    var idMeal: String,
    var strMeal: String?=null,
    var strDrinkAlternate: String?=null,
    var strCategory: String?=null,
    var strArea: String?=null,
    var strInstructions: String?=null,
    var strMealThumb: String?=null,
    var strTags: String?=null,
    var strYoutube: String?=null,
    var strIngredient1: String?=null,
    var strIngredient2: String?=null,
    var strIngredient3: String?=null,
    var strIngredient4: String?=null,
    var strIngredient5: String?=null,
    var strIngredient6: String?=null,
    var strIngredient7: String?=null,
    var strIngredient8: String?=null,
    var strIngredient9: String?=null,
    var strIngredient10: String?=null,
    var strIngredient11: String?=null,
    var strIngredient12: String?=null,
    var strIngredient13: String?=null,
    var strIngredient14: String?=null,
    var strIngredient15: String?=null,
    var strIngredient16: String?=null,
    var strIngredient17: String?=null,
    var strIngredient18: String?=null,
    var strIngredient19: String?=null,
    var strIngredient20: String?=null,
    var strMeasure1: String?=null,
    var strMeasure2: String?=null,
    var strMeasure3: String?=null,
    var strMeasure4: String?=null,
    var strMeasure5: String?=null,
    var strMeasure6: String?=null,
    var strMeasure7: String?=null,
    var strMeasure8: String?=null,
    var strMeasure9: String?=null,
    var strMeasure10: String?=null,
    var strMeasure11: String?=null,
    var strMeasure12: String?=null,
    var strMeasure13: String?=null,
    var strMeasure14: String?=null,
    var strMeasure15: String?=null,
    var strMeasure16: String?=null,
    var strMeasure17: String?=null,
    var strMeasure18: String?=null,
    var strMeasure19: String?=null,
    var strMeasure20: String?=null,
    var strSource: String?=null,
    var dateModified: String?=null
)