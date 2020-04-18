package com.example.eatanddrink.database

import android.content.Context
import androidx.room.*
import com.example.eatanddrink.model.drink.DrinkDataX
import com.example.eatanddrink.model.meal.Meal
import com.example.eatanddrink.util.DRINK_DATABASE

@Dao
interface DrinkDao{
    @Query("Select * from DrinkDataX where idDrink= :id")
    suspend fun getDrink(id: String): DrinkDataX?

    @Query("Select * from DrinkDataX ")
    suspend fun getAllDrinks(): List<DrinkDataX>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drink: DrinkDataX)

    @Query("Delete from DrinkDataX where idDrink= :id")
    suspend fun removeDrink(id:String)
}

@Database(entities = [DrinkDataX::class],version = 1)
abstract class DrinkDatabase():RoomDatabase(){
    abstract val drinkDao:DrinkDao
}
private lateinit var INSTANCE :DrinkDatabase
fun getDrinkDatabase (context: Context): DrinkDatabase{
    synchronized(DrinkDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE=Room.databaseBuilder(context.applicationContext,
                DrinkDatabase::class.java,
                DRINK_DATABASE).build()
        }
    }
    return INSTANCE
}