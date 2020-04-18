package com.example.eatanddrink.network

import com.example.eatanddrink.model.drink.DrinkCategory
import com.example.eatanddrink.model.drink.DrinkData
import com.example.eatanddrink.model.drink.Drinks
import com.example.eatanddrink.model.meal.MealCategory
import com.example.eatanddrink.model.meal.MealData
import com.example.eatanddrink.model.meal.Meals
import com.example.eatanddrink.util.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealServices{
    @GET(FILTER)
    fun getMealListAsync(@Query("c")categoryName:String): Deferred<Meals>
    @GET(CATEGORIES)
    fun getCategoryAsync(): Deferred<MealCategory>
    @GET(LOOK_UP)
    fun getMealAsync(@Query("i")i:String): Deferred<MealData>
    @GET(RANDOM)
    fun getRandomMealAsync():Deferred<MealData>
}

object MealRetrofitApi{
    private val retrofitApi= Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(MEAL_BASE_URL)
        .build()
    val instance: MealServices = retrofitApi.create(MealServices::class.java)
}

interface DrinkServices {
    @GET(DRINK_LIST)
    fun getCategoryAsync() : Deferred<DrinkCategory>
    @GET(FILTER)
    fun getDrinkListAsync(@Query("c")categoryName: String):Deferred<Drinks>
    @GET(LOOK_UP)
    fun getDrinkAsync(@Query("i") drinkId:String):Deferred<DrinkData>
    @GET(RANDOM)
    fun getRandomDrinkAsync():Deferred<DrinkData>
}

object DrinkRetrofitApi{
    private val retrofitApi=Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(DRINK_BASE_URL)
        .build()
    val instance : DrinkServices= retrofitApi.create(DrinkServices::class.java)
}


