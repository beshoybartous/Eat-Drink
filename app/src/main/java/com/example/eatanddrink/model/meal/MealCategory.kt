package com.example.eatanddrink.model.meal
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

//https://www.themealdb.com/api/json/v1/1/categories.php
@Keep
data class MealCategory(
    val categories: List<Category>
)

@Keep
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idCategory)
        parcel.writeString(strCategory)
        parcel.writeString(strCategoryDescription)
        parcel.writeString(strCategoryThumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}