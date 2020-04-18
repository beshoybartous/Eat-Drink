package com.example.eatanddrink.repository.meal

import androidx.lifecycle.MutableLiveData
import com.example.eatanddrink.model.meal.Category
import com.example.eatanddrink.model.meal.MealX
import com.example.eatanddrink.network.MealRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealRepository {

    var categoryMutableLiveData = MutableLiveData<List<Category>>()
    var mealsMutableLiveData = MutableLiveData<List<MealX>>()

    suspend fun getMealList(categoryName : String){
        withContext(Dispatchers.IO){
            val mealList=MealRetrofitApi.instance.getMealListAsync(categoryName)
            try {
                val result=mealList.await()
                mealsMutableLiveData.postValue(result.meals)
            }
            catch (e: Exception) {
            }
        }
    }
    suspend fun getCategory(){
        withContext(Dispatchers.IO){
            val categories=MealRetrofitApi.instance.getCategoryAsync()
            try {
                val result=categories.await()
                categoryMutableLiveData.postValue(result.categories)
            }
            catch (e: java.lang.Exception){
            }
        }
    }



}