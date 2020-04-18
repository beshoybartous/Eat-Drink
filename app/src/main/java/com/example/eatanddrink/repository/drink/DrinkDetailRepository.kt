package com.example.eatanddrink.repository.drink

import androidx.lifecycle.MutableLiveData
import com.example.eatanddrink.database.DrinkDatabase
import com.example.eatanddrink.model.drink.DrinkDataX
import com.example.eatanddrink.network.DrinkRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DrinkDetailRepository (private val drinkDatabase : DrinkDatabase) {
    val roomDrink : MutableLiveData<DrinkDataX?> = MutableLiveData<DrinkDataX?>()
    suspend fun getRoomDrink(drinkId : String) {
        withContext(Dispatchers.IO){

            try {
                roomDrink.postValue(drinkDatabase.drinkDao.getDrink(drinkId))
            }
            catch (e: Exception){

            }
        }
    }
    val allRoomDrink  : MutableLiveData<List<DrinkDataX>?> =  MutableLiveData<List<DrinkDataX>?>()

    suspend fun getAllRoomDrinks() {
        withContext(Dispatchers.IO){
            try {
                allRoomDrink.postValue(drinkDatabase.drinkDao.getAllDrinks())
            }
            catch (e: Exception){

            }
        }
    }

    suspend fun insertDrink(drink : DrinkDataX){

        drinkDatabase.drinkDao.insertDrink(drink)
    }

    suspend fun removeDrink(id:String){
        withContext(Dispatchers.IO){
            try {
                drinkDatabase.drinkDao.removeDrink(id)
            }
            catch (e: Exception){

            }
        }
    }

    var drinkDataMutableLiveData = MutableLiveData<DrinkDataX>()

    suspend fun getDrink(drinkId : String){
        withContext(Dispatchers.IO){
            val mealList= DrinkRetrofitApi.instance.getDrinkAsync(drinkId)
            try {
                val result=mealList.await()
                drinkDataMutableLiveData.postValue(result.drinks[0])
            }
            catch (e: Exception) {
            }
        }
    }
    var randomDrinkMutableLiveData = MutableLiveData<DrinkDataX>()

    suspend fun getRandomDrink(){
        withContext(Dispatchers.IO){
            val randomDrink=DrinkRetrofitApi.instance.getRandomDrinkAsync()
            try {
                val result=randomDrink.await()
                randomDrinkMutableLiveData.postValue(result.drinks[0])
            }
            catch (e:Exception){

            }
        }
    }
}