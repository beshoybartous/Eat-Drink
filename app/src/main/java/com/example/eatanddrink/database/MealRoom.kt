package com.example.eatanddrink.database

import android.content.Context
import androidx.room.*
import com.example.eatanddrink.model.meal.Meal
import com.example.eatanddrink.util.MEAL_DATABASE

@Dao
interface MealDao{
    @Query("Select * from Meal where idMeal= :id")
    suspend fun getMeal(id: String): Meal?

    @Query("Select * from Meal ")
    suspend fun getAllMeals(): List<Meal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("Delete from Meal where idMeal= :id")
    suspend fun removeMeal(id:String)
}

@Database(entities = [Meal::class] , version = 1)
abstract class MealDataBase : RoomDatabase(){
    abstract val mealDao:MealDao
}

private lateinit var INSTANCE :MealDataBase
fun getMealDatabase(context: Context): MealDataBase{
    synchronized(MealDataBase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE=Room.databaseBuilder(context.applicationContext,
            MealDataBase::class.java,
                MEAL_DATABASE).build()
        }
    }
    return INSTANCE
}