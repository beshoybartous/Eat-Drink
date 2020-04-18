package com.example.eatanddrink.util

import android.util.Log
import com.example.eatanddrink.model.drink.DrinkData
import com.example.eatanddrink.model.drink.DrinkDataX
import com.example.eatanddrink.model.meal.Meal

fun getMealIngredients(meal : Meal):List<Pair<String, String>> {

    val ingredientList  = mutableListOf<Pair<String,String>>()
    if (isNullOrEmpty (meal.strIngredient1)&&isNullOrEmpty (meal.strMeasure1)){
        ingredientList.add(Pair(meal.strIngredient1,meal.strMeasure1) as Pair<String, String>)
    }
    if (isNullOrEmpty (meal.strIngredient2)&&isNullOrEmpty (meal.strMeasure2)){
        ingredientList.add(Pair(meal.strIngredient2,meal.strMeasure2) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient3)&&isNullOrEmpty (meal.strMeasure3)){
        ingredientList.add(Pair(meal.strIngredient3,meal.strMeasure3) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient4)&&isNullOrEmpty (meal.strMeasure4)){
        ingredientList.add(Pair(meal.strIngredient4,meal.strMeasure4) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient5)&&isNullOrEmpty (meal.strMeasure5)){
        ingredientList.add(Pair(meal.strIngredient5,meal.strMeasure5) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient6)&&isNullOrEmpty (meal.strMeasure6)){
        ingredientList.add(Pair(meal.strIngredient6,meal.strMeasure6) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient7)&&isNullOrEmpty (meal.strMeasure7)){
        ingredientList.add(Pair(meal.strIngredient7,meal.strMeasure7) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient8)&&isNullOrEmpty (meal.strMeasure8)){
        ingredientList.add(Pair(meal.strIngredient8,meal.strMeasure8) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient9)&&isNullOrEmpty (meal.strMeasure9)){
        ingredientList.add(Pair(meal.strIngredient9,meal.strMeasure9) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient10)&&isNullOrEmpty (meal.strMeasure10)){
        ingredientList.add(Pair(meal.strIngredient10,meal.strMeasure10) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient11)&&isNullOrEmpty (meal.strMeasure11)){
        ingredientList.add(Pair(meal.strIngredient11,meal.strMeasure11) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient12)&&isNullOrEmpty (meal.strMeasure12)){
        ingredientList.add(Pair(meal.strIngredient12,meal.strMeasure12) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient13)&&isNullOrEmpty (meal.strMeasure13)){
        ingredientList.add(Pair(meal.strIngredient13,meal.strMeasure13) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient14)&&isNullOrEmpty (meal.strMeasure14)){
        ingredientList.add(Pair(meal.strIngredient14,meal.strMeasure14) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient15)&&isNullOrEmpty (meal.strMeasure15)){
        ingredientList.add(Pair(meal.strIngredient15,meal.strMeasure15) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient16)&&isNullOrEmpty (meal.strMeasure16)){
        ingredientList.add(Pair(meal.strIngredient16,meal.strMeasure16) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient17)&&isNullOrEmpty (meal.strMeasure17)){
        ingredientList.add(Pair(meal.strIngredient17,meal.strMeasure17) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient18)&&isNullOrEmpty (meal.strMeasure18)){
        ingredientList.add(Pair(meal.strIngredient18,meal.strMeasure18) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient19)&&isNullOrEmpty (meal.strMeasure19)){
        ingredientList.add(Pair(meal.strIngredient19,meal.strMeasure19) as Pair<String, String>)

    }
    if (isNullOrEmpty (meal.strIngredient20)&&isNullOrEmpty (meal.strMeasure20)){
        ingredientList.add(Pair(meal.strIngredient20,meal.strMeasure20) as Pair<String, String>)


    }
    return ingredientList
}
fun getDrinkIngredients(drink : DrinkDataX?):List<Pair<String, String>>{

    val ingredientList  = mutableListOf<Pair<String,String>>()
    if(drink!=null) {
        if (isNullOrEmpty(drink.strIngredient1) && isNullOrEmpty(drink.strMeasure1)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient1,
                    drink.strIngredient1
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient2) && isNullOrEmpty(drink.strMeasure2)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient2,
                    drink.strIngredient2
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient3) && isNullOrEmpty(drink.strMeasure3)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient3,
                    drink.strIngredient3
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient4) && isNullOrEmpty(drink.strMeasure4)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient4,
                    drink.strIngredient4
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient5) && isNullOrEmpty(drink.strMeasure5)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient5,
                    drink.strIngredient5
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient6) && isNullOrEmpty(drink.strMeasure6)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient6,
                    drink.strIngredient6
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient7) && isNullOrEmpty(drink.strMeasure7)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient7,
                    drink.strIngredient7
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient8) && isNullOrEmpty(drink.strMeasure8)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient8,
                    drink.strIngredient8
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient9) && isNullOrEmpty(drink.strMeasure9)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient9,
                    drink.strIngredient9
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient10) && isNullOrEmpty(drink.strMeasure10)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient10,
                    drink.strIngredient10
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient11) && isNullOrEmpty(drink.strMeasure11)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient11,
                    drink.strIngredient11
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient12) && isNullOrEmpty(drink.strMeasure12)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient12,
                    drink.strIngredient12
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient13) && isNullOrEmpty(drink.strMeasure13)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient13,
                    drink.strIngredient13
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient14) && isNullOrEmpty(drink.strMeasure14)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient14,
                    drink.strIngredient14
                ) as Pair<String, String>
            )
        }
        if (isNullOrEmpty(drink.strIngredient15) && isNullOrEmpty(drink.strMeasure15)) {
            ingredientList.add(
                Pair(
                    drink.strIngredient15,
                    drink.strIngredient15
                ) as Pair<String, String>
            )
        }
    }
    return ingredientList

}

fun isNullOrEmpty(str: String?): Boolean {
    if (str != null && !str.isEmpty())
        return true
    return false
}


fun getYoutubeCode(strYoutube : String) : String{
   return strYoutube.split("v=")[1]
}
