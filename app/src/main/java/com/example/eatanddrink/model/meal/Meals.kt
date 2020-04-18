package com.example.eatanddrink.model.meal
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

//https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood
@Keep
data class Meals(
    val meals: List<MealX>
)
@Keep
data class MealX(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMeal)
        parcel.writeString(strMeal)
        parcel.writeString(strMealThumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MealX> {
        override fun createFromParcel(parcel: Parcel): MealX {
            return MealX(parcel)
        }

        override fun newArray(size: Int): Array<MealX?> {
            return arrayOfNulls(size)
        }
    }
}