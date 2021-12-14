package e2e4.ru.what_are_we_eating_today.repo

import e2e4.ru.what_are_we_eating_today.models.domain.Food
import e2e4.ru.what_are_we_eating_today.models.domain.FoodType

interface WheelRepo {
    suspend fun getFoodTypes(): List<FoodType>
    fun insertFoodType(foodType: FoodType)
    suspend fun getFood(foodType: FoodType): Food
    fun deleteAllFoodTypes()
}