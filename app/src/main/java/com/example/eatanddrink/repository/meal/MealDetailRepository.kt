package com.example.eatanddrink.repository.meal

import androidx.lifecycle.MutableLiveData
import com.example.eatanddrink.database.MealDataBase
import com.example.eatanddrink.model.meal.Meal
import com.example.eatanddrink.network.MealRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MealDetailRepository (private val database : MealDataBase) {

    val roomMeal : MutableLiveData<Meal?> = MutableLiveData<Meal?>()
    suspend fun getRoomMeal(mealId : String) {
        withContext(Dispatchers.IO){

            try {
                roomMeal.postValue(database.mealDao.getMeal(mealId))
            }
            catch (e:Exception){

            }
        }
    }
    val allRoomMeal  :MutableLiveData<List<Meal>?> =  MutableLiveData<List<Meal>?>()

    suspend fun getAllRoomMeals() {
        withContext(Dispatchers.IO){
            try {
                allRoomMeal.postValue(database.mealDao.getAllMeals())
            }
            catch (e:Exception){

            }
        }
    }

   suspend fun insertMeal(meal : Meal){

        database.mealDao.insertMeal(meal)
    }

    suspend fun removeMeal(id:String){
        withContext(Dispatchers.IO){
            try {
                database.mealDao.removeMeal(id)
            }
            catch (e:Exception){

            }
        }
    }

    val mealDataMutableLiveData = MutableLiveData<Meal>()

    suspend fun getMeal(mealId : String){
        withContext(Dispatchers.IO){
            val mealList=MealRetrofitApi.instance.getMealAsync(mealId)
            try {
                val result=mealList.await()
                mealDataMutableLiveData.postValue(result.meals?.get(0))
            }
            catch (e: Exception) {
            }
        }
    }

    val randomMealMutableLiveData = MutableLiveData<Meal>()

    suspend fun getRandomMeal(){
        withContext(Dispatchers.IO){
            val randomMeal=MealRetrofitApi.instance.getRandomMealAsync()
            try {
                val result=randomMeal.await()
                randomMealMutableLiveData.postValue(result.meals?.get(0))
            }
            catch (e:Exception){

            }
        }
    }
}