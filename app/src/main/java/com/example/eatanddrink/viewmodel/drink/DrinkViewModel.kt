package com.example.eatanddrink.viewmodel.drink

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eatanddrink.database.getDrinkDatabase
import com.example.eatanddrink.model.drink.DrinkCategoryX
import com.example.eatanddrink.model.drink.DrinkDataX
import com.example.eatanddrink.model.drink.DrinksX
import com.example.eatanddrink.repository.drink.DrinkDetailRepository
import com.example.eatanddrink.repository.drink.DrinkRepository
import com.example.eatanddrink.util.getDrinkIngredients
import com.example.eatanddrink.util.getYoutubeCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class DrinkViewModel (application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope= CoroutineScope(viewModelJob+ Dispatchers.Main)
    private val drinkRepository = DrinkRepository()
    private val drinkDetailRepository =
        DrinkDetailRepository(
            getDrinkDatabase(application)
        )
     var _loaded=MutableLiveData<Boolean>()

    init {
        _loaded.value=false
    }

    val loaded:LiveData<Boolean>
            get()=_loaded
    fun setLoaded(boolean: Boolean){
        _loaded.value=boolean
    }
    /*
    * get List of category from api
    * */
    val categoryList : LiveData<List<DrinkCategoryX>>
        get() = _categoryList
    private val _categoryList = MutableLiveData<List<DrinkCategoryX>>()

    fun setCategory() {
        viewModelScope.launch {
            try {
                drinkRepository.getCategory()
                _categoryList.value=drinkRepository.categoryMutableLiveData.value
            }
            catch (e: Exception){
                Log.d("error",e.message!!)
            }
        }
    }


    /*
    *
    * get List of meals of current category
    *
    * */
    private val _drinkX= MutableLiveData<List<DrinksX>>()
    val drinksX: LiveData<List<DrinksX>>
        get() = _drinkX

    fun setDrinkList(categoryName:String){
        viewModelScope.launch {
            try {
                drinkRepository.getDrinkList(categoryName)
                _drinkX.postValue(drinkRepository.drinksMutableLiveData.value)
            }
            catch (e: Exception){
                Log.d("error",e.message!!)
            }
        }
    }


    /*
* get random meal
*
* */
    private val _randomDrink=MutableLiveData<DrinkDataX>()
    val randomDrink : LiveData<DrinkDataX>
        get() = _randomDrink
    fun getRandomDrink(newRandom:Boolean){
        viewModelScope.launch {
            try {
                if(newRandom || _randomDrink.value==null) {
                    drinkDetailRepository.getRandomDrink()
                    _randomDrink.postValue(drinkDetailRepository.randomDrinkMutableLiveData.value)
                    getDrinkUtils(_randomDrink)
                }
            }
            catch (e:Exception){
                Log.d("error",e.toString())
            }
        }
    }


/*
* get meal data api
*
* */

    private val _drink= MutableLiveData<DrinkDataX>()
    val drink : LiveData<DrinkDataX>
        get() = _drink
    private val _ingredients=MutableLiveData<List<Pair<String,String>>>()
    val ingredients :LiveData<List<Pair<String,String>>>
        get() = _ingredients


    fun getDrink(favoriteCheck:Boolean,mealId:String){
        viewModelScope.launch {
            try {
                drinkDetailRepository.getDrink(mealId)
                _drink.postValue(drinkDetailRepository.drinkDataMutableLiveData.value)
                if(favoriteCheck){
                    Log.i("testroom","insert2")
                    viewModelScope.launch {
                        insertDrink(_drink.value!!)
                    }
                }
                else{
                    getDrinkUtils(_drink)
                }
            }
            catch (e: Exception){
                Log.d("error",e.toString())
            }
        }
    }
    fun getDrinkUtils(value: LiveData<DrinkDataX>) {
        viewModelScope.launch {
            try {
                _ingredients.value = getDrinkIngredients(value.value!!)
            } catch (e: Exception) {
//                Log.d("error", e.message!!.toString())
            }
        }
    }



    /*
    * get meal from room
    *
    *
    * */
    private val _roomDrink= MutableLiveData<DrinkDataX>()
    val roomDrink : LiveData<DrinkDataX>
        get() = _roomDrink

    fun getRoomDrink(favoriteCheck:Boolean,idMeal:String){
        viewModelScope.launch {
            try {
                drinkDetailRepository.getRoomDrink(idMeal)
                _roomDrink.postValue(drinkDetailRepository.roomDrink.value)
                if(favoriteCheck){
                    getDrinkUtils(_roomDrink)
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun insertDrink(drink: DrinkDataX) {
        viewModelScope.launch {
            try {
                drinkDetailRepository.insertDrink(drink)

            }
            catch (e: Exception){
            }
        }
    }

    private val _roomAllDrink= MutableLiveData<List<DrinkDataX>?>()
    val roomAllDrink : LiveData<List<DrinkDataX>?>
        get() = _roomAllDrink

    fun getAllRoomDrinks(){
        viewModelScope.launch {
            try {
                drinkDetailRepository.getAllRoomDrinks()
                _roomAllDrink.postValue(drinkDetailRepository.allRoomDrink.value)
            }
            catch (e: Exception){

            }
        }
    }

    fun removeDrink(id:String){
        viewModelScope.launch {
            try {
                drinkDetailRepository.removeDrink(id)
                _roomDrink.value=null
                getAllRoomDrinks()
            }
            catch (e: Exception){
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}