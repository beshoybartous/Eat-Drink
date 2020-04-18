package com.example.eatanddrink.repository.drink

import androidx.lifecycle.MutableLiveData
import com.example.eatanddrink.model.drink.DrinkCategoryX
import com.example.eatanddrink.model.drink.DrinksX
import com.example.eatanddrink.network.DrinkRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DrinkRepository {

    var categoryMutableLiveData = MutableLiveData<List<DrinkCategoryX>>()
    var drinksMutableLiveData = MutableLiveData<List<DrinksX>>()

    suspend fun getDrinkList(categoryName : String){
        withContext(Dispatchers.IO){
            val mealList= DrinkRetrofitApi.instance.getDrinkListAsync(categoryName)
            try {
                val result=mealList.await()
                drinksMutableLiveData.postValue(result.drinks)
            }
            catch (e: Exception) {
            }
        }
    }
    suspend fun getCategory(){
        withContext(Dispatchers.IO){
            val categories= DrinkRetrofitApi.instance.getCategoryAsync()
            try {
                val result=categories.await()
                categoryMutableLiveData.postValue(result.drinks)
            }
            catch (e: java.lang.Exception){
            }
        }
    }



}