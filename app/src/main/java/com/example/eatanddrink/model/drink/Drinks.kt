package com.example.eatanddrink.model.drink
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class Drinks(
    val drinks: List<DrinksX>
)

@Keep
data class DrinksX(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
) :  Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idDrink)
        parcel.writeString(strDrink)
        parcel.writeString(strDrinkThumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DrinksX> {
        override fun createFromParcel(parcel: Parcel): DrinksX {
            return DrinksX(parcel)
        }

        override fun newArray(size: Int): Array<DrinksX?> {
            return arrayOfNulls(size)
        }
    }
}