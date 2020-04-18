package com.example.eatanddrink.model.drink
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class DrinkCategory(
    val drinks: List<DrinkCategoryX>
)

@Keep
data class DrinkCategoryX(
    val strCategory: String
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(strCategory)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DrinkCategoryX> {
        override fun createFromParcel(parcel: Parcel): DrinkCategoryX {
            return DrinkCategoryX(parcel)
        }

        override fun newArray(size: Int): Array<DrinkCategoryX?> {
            return arrayOfNulls(size)
        }
    }
}