package com.example.eatanddrink.viewmodel.meal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eatanddrink.database.getMealDatabase
import com.example.eatanddrink.model.meal.Category
import com.example.eatanddrink.model.meal.Meal
import com.example.eatanddrink.model.meal.MealX
import com.example.eatanddrink.repository.meal.MealRepository
import com.example.eatanddrink.repository.meal.MealDetailRepository
import com.example.eatanddrink.util.getMealIngredients
import com.example.eatanddrink.util.getYoutubeCode
import kotlinx.coroutines.*
import java.lang.Exception

class MealViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope= CoroutineScope(viewModelJob+Dispatchers.Main)
    private val mealRepository = MealRepository()
    private val mealDetailRepository =
        MealDetailRepository(
            getMealDatabase(application)
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
    val categoryList :LiveData<List<Category>>
        get() = _categoryList
    private val _categoryList =MutableLiveData<List<Category>>()

    fun setCategory() {
        viewModelScope.launch {
            try {
                mealRepository.getCategory()
                _categoryList.value=mealRepository.categoryMutableLiveData.value
            }
            catch (e:Exception){
                Log.d("error",e.message!!)
            }
        }
    }


    /*
    *
    * get List of meals of current category
    *
    * */
    private val _mealX= MutableLiveData<List<MealX>>()
    val mealX: LiveData<List<MealX>>
    get() = _mealX

    fun setMealList(categoryName:String){
    viewModelScope.launch {
        try {
            mealRepository.getMealList(categoryName)
            _mealX.postValue(mealRepository.mealsMutableLiveData.value)
        }
        catch (e:Exception){
            Log.d("error",e.message!!)
        }
    }
    }




/*
* get random meal
*
* */
private val _randomMeal=MutableLiveData<Meal>()
    val randomMeal : LiveData<Meal>
        get() = _randomMeal
    fun getRandomMeal(newRandom:Boolean){
        viewModelScope.launch {
            try {
                if(newRandom || _randomMeal.value==null) {
                    mealDetailRepository.getRandomMeal()
                    _randomMeal.postValue(mealDetailRepository.randomMealMutableLiveData.value)
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

    private val _meal=MutableLiveData<Meal>()
    val meal : LiveData<Meal>
        get() = _meal
    private val _ingredients=MutableLiveData<List<Pair<String,String>>>()
    val ingredients :LiveData<List<Pair<String,String>>>
        get() = _ingredients
    private val _youtubeCode=MutableLiveData<String?>()
    val youtubeCode :LiveData<String?>
        get() = _youtubeCode

    fun removeYotube(){
        _youtubeCode.value=null
    }

     fun getMeal(favoriteCheck:Boolean,mealId:String){
        viewModelScope.launch {
            try {
                mealDetailRepository.getMeal(mealId)
                _meal.postValue(mealDetailRepository.mealDataMutableLiveData.value)
                if(favoriteCheck){
                    Log.i("testroom","insert2")
                    viewModelScope.launch {
                        insertMeal(_meal.value!!)
                    }
                }
                else{
                    getMealUtils(_meal )
                }
            }
            catch (e:Exception){
                Log.d("error",e.toString())
            }
        }
    }
    fun getMealUtils(value: LiveData<Meal>) {
        Log.i("entered", "entered")

        viewModelScope.launch {
            try {

                _ingredients.value = getMealIngredients(value.value!!)
                _youtubeCode.value = value.value!!.strYoutube?.let { getYoutubeCode(it) }
            } catch (e: Exception) {
//                Log.d("error", e.message!!)
            }
        }
    }


    /*
    * get meal from room
    *
    *
    * */
     private val _roomMeal=MutableLiveData<Meal>()
     val roomMeal : LiveData<Meal>
         get() = _roomMeal

     fun getRoomMeal(favoriteCheck:Boolean,idMeal:String){
         viewModelScope.launch {
             try {
                 mealDetailRepository.getRoomMeal(idMeal)
                 _roomMeal.postValue(mealDetailRepository.roomMeal.value)
                 if(favoriteCheck){
                     getMealUtils(_roomMeal)
                 }
             }
             catch (e:Exception){
             }
         }
    }

     fun insertMeal(meal: Meal) {
        viewModelScope.launch {
            try {
                mealDetailRepository.insertMeal(meal)

            }
            catch (e:Exception){
            }
        }
    }

    private val _roomAllMeal=MutableLiveData<List<Meal>?>()
    val roomAllMeal : LiveData<List<Meal>?>
        get() = _roomAllMeal

     fun getAllRoomMeals(){
        viewModelScope.launch {
            try {
                mealDetailRepository.getAllRoomMeals()
                _roomAllMeal.postValue(mealDetailRepository.allRoomMeal.value)
                Log.d("testroom1","get all meals")

            }
            catch (e:Exception){

            }
        }
    }

    fun removeMeal(id:String){
        viewModelScope.launch {
            try {
                mealDetailRepository.removeMeal(id)
                _roomMeal.value=null
                getAllRoomMeals()
            }
            catch (e:Exception){
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}